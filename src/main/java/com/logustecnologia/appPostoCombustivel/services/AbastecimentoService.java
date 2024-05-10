/**
 * 
 */
package com.logustecnologia.appPostoCombustivel.services;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logustecnologia.appPostoCombustivel.DTO.AbastecimentoDTO;
import com.logustecnologia.appPostoCombustivel.model.Abastecimento;
import com.logustecnologia.appPostoCombustivel.model.Bomba;
import com.logustecnologia.appPostoCombustivel.model.Veiculo;
import com.logustecnologia.appPostoCombustivel.model.enums.TipoCombustivel;
import com.logustecnologia.appPostoCombustivel.repositories.AbastecimentoRepository;
import com.logustecnologia.appPostoCombustivel.services.exception.DataIntegrityViolationException;
import com.logustecnologia.appPostoCombustivel.services.exception.ObjectNotFoundException;


/**
 * @author Petronilo Padilha
 * email: nilopadilha@gmail.com
 * Telefone: 84 99498-4982
 *
 */
@Service
public class AbastecimentoService {
	
	@Autowired
	private AbastecimentoRepository repository;
	
	private Double totalLitrosEtanal;
	private Double totalVendidoEtanal;
	private Double totalTempoAbastecendoEtanol;
	
	private Double totalLitrosGasolina;
	private Double totalVendidoGasolina;
	private Double totalTempoAbastecendoGasolina;
	
	private Double totalLitrosOutros;
	private Double totalVendidoOutros;
	private Double totalTempoAbastecendoOutros;
	
	/**
	 * realiza a busca de um Abastecimento por Id
	 * 
	 * @param id
	 * @return Abastecimento
	 */
	public Abastecimento findById( Long id )  {	
		Optional<Abastecimento> obj = repository.findById(id);	
		return obj.orElseThrow( () -> new ObjectNotFoundException(
				"Objeto não encontrato! Id: " + id + ", Tipo: " + Abastecimento.class.getName()));
	}
	
	/**
	 * mostra todos os abastecimentos realizados
	 * 
	 * @return List<Abastecimento> 
	 */
	public List<Abastecimento> findAll() {
		return repository.findAll();
	}
	
	/**
	 * Cria um novo Abastecimento
	 * 
	 * @param abastecimento
	 * @return Abastecimento
	 */
	public Abastecimento create( Bomba bomba, Veiculo veiculo, Double qtdLitros ) {
		
		Abastecimento abastecimento = new Abastecimento();
		abastecimento.setBomba(bomba);
		abastecimento.setVeiculo(veiculo);
		abastecimento.setDate( new Date() );
		abastecimento.setQuantidadeLitros(qtdLitros);
		abastecimento.setValor(qtdLitros * bomba.getPreco());
		
		return this.repository.save(abastecimento);
	}
	
	/**
	 * Atualiza um Abastecimento
	 * 
	 * @param obj - Abastecimento
	 * @return Abastecimento
	 */
	public Abastecimento update( Abastecimento obj) {
		Abastecimento novoAbast = findById(obj.getId());
		return repository.save(novoAbast);
	}
	
	/**
	 * Deleta um abastecimento do Posto
	 * 
	 * @param id
	 */
	public void delete( Long id ) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch ( DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException
			( "O abastecimento não pode ser deletado! Já exitem Abastecimentos vinculados ." );
		}
	}
	
	/**
	 * Variáveis para imprimir os valores totais no relatorio de 
	 */
	private void carregarVariaveisRelatorioAbast() {
		
		totalLitrosEtanal = 0.0;
		totalVendidoEtanal = 0.0;
		totalTempoAbastecendoEtanol = 0.0;
		
		totalLitrosGasolina = 0.0;
		totalVendidoGasolina = 0.0;
		totalTempoAbastecendoGasolina = 0.0;
			
		totalLitrosOutros = 0.0;
		totalVendidoOutros = 0.0;
		totalTempoAbastecendoOutros = 0.0;
		
		List<Abastecimento> listA = findAll();
		for (Abastecimento a : listA) {	
		
			if(a.getBomba().getCombustivel().getTipoCombustivel() == TipoCombustivel.ALCOOL ){
				totalLitrosEtanal += a.getQuantidadeLitros();
				totalVendidoEtanal += a.getValor();
				totalTempoAbastecendoEtanol =+ a.getQuantidadeLitros() / a.getBomba().getVelocidadeAbastece(); 
			}else if(a.getBomba().getCombustivel().getTipoCombustivel() == TipoCombustivel.GASOLINAC ) {
				totalLitrosGasolina += a.getQuantidadeLitros();
				totalVendidoGasolina += a.getValor();
				totalTempoAbastecendoGasolina =+ a.getQuantidadeLitros() / a.getBomba().getVelocidadeAbastece();
			}else {
				totalLitrosOutros += a.getQuantidadeLitros();
				totalVendidoOutros += a.getValor();
				totalTempoAbastecendoOutros += a.getQuantidadeLitros() / a.getBomba().getVelocidadeAbastece();
			}
		}
	}
	
	/**
	 * Método carrega lista de Strings que podem ser iteradas
	 * imprimindo relatório individual de cada abastecimento assim como o resumo total
	 * 
	 * @return list - List<String>
	 */
	public List<String> relatorioCompletoAbastecimento() {
			
		carregarVariaveisRelatorioAbast();
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy-hh:mm");	
		List<String> list = new ArrayList<String>();
		list.add("\n ##### ABASTECIMETNOS ##### \n");
				
		List<Abastecimento> listA = findAll();
		for (Abastecimento a : listA) {		
			String data = "Data: " + formatter.format(a.getDate());
			String infoCarro = a.getVeiculo().getModelo() + " " + a.getVeiculo().getNome() + ", placa " +  a.getVeiculo().getPlaca();
			String infoConbustivel = " foi abastecido com " + a.getQuantidadeLitros() + " de " + a.getBomba().getCombustivel().getTipoCombustivel();
			list.add(data + " - Veiculo: " + infoCarro + infoConbustivel);		
		}
		
		list.add("\n ##### Relatório de Abastecimento ##### \n ");
		list.add("Total de litros de ETANOL abastecido = " + totalLitrosEtanal);
		list.add("Total de vendido de ETANOL = " + totalVendidoEtanal);
		list.add("Tempoo abastecendo ETANOL = " + totalTempoAbastecendoEtanol);
		
		list.add("Total de litros de GASOLINA abastecido = " + totalLitrosGasolina);
		list.add("Total de vendido de GASOLINA = " + totalVendidoGasolina);
		list.add("Tempo abastecendo GASOLINA = " + totalTempoAbastecendoGasolina);		
		
		list.add("Total de litros de OUTROS COMBUSTIVEIS abastecido = " + totalLitrosOutros);
		list.add("Total de vendido de OUTROS COMBUSTIVEIS = " + totalVendidoOutros);
		list.add("Tempo abastecendo OUTROS COMBUSTIVEIS = " + totalTempoAbastecendoOutros);	

		return list;
	}
	
	
	/**
	 * Armazena os valores totais de cada tipo em uma respectiva key 
	 * do HashMap. Usado no relatório do JasperReport.
	 * 
	 * @return HashMap<String,Object>
	 */
	public HashMap<String,Object> resumoAbastecimento() {
		
		carregarVariaveisRelatorioAbast();;
			
		HashMap<String,Object> params = new HashMap<String,Object>();						
	
		params.put( "totalLitrosEtanal", 
				"Litros de ETANOL: " + new DecimalFormat(".##").format(totalLitrosEtanal) );
		params.put( "totalVendidoEtanal", 
				"Faturamento de ETANOL: " + new DecimalFormat(".##").format(totalVendidoEtanal) );
		params.put( "totalTempoAbastecendoEtanol", 
				"Tempo abastecendo ETANOL: " + new DecimalFormat(".##").format(totalTempoAbastecendoEtanol)  + " minutos");
		
		params.put( "totalLitrosGasolina", 
				"Litros de GASOLINA: " + new DecimalFormat(".##").format(totalLitrosGasolina) );
		params.put( "totalVendidoGasolina", 
				"Faturamento de GASOLINA: " + new DecimalFormat(".##").format(totalVendidoGasolina) );
		params.put( "totalTempoAbastecendoGasolina", 
				"Tempo abastecendo GASOLINA: " + new DecimalFormat(".##").format(totalTempoAbastecendoGasolina)  + " minutos");		
		
		params.put( "totalLitrosOutros", 
				"Litros de OUTROS COMBUSTIVEIS: " + new DecimalFormat(".##").format(totalLitrosOutros) );
		params.put( "totalVendidoOutros", 
				"Faturamento de OUTROS COMBUSTIVEIS: " + new DecimalFormat(".##").format(totalVendidoOutros) );
		params.put( "totalTempoAbastecendoOutros", 
				"Tempo abastecendo OUTROS COMBUSTIVEIS: " + new DecimalFormat(".##").format(totalTempoAbastecendoOutros)  + " minutos");	

		return params;
	}
	
	/**
	 * Cria uma lista de classes AbastecimentoDTO
	 * AbastecimentoDTO é modelada para facilitar a impressão dos dados no relatório.
	 * 
	 * @return listaAbastecimentoDTO - List<AbastecimentoDTO>
	 */
	public List<AbastecimentoDTO> prepararAbastecimentoDTO() {

		List<AbastecimentoDTO> listaAbastecimentoDTO = new ArrayList<AbastecimentoDTO>();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy");	

		List<Abastecimento> listA = findAll();
		for (Abastecimento a : listA) {	
					
			String data = formatter.format(a.getDate());
			String placa = a.getVeiculo().getPlaca();
			String infoCarro = a.getVeiculo().getModelo() + " " + a.getVeiculo().getNome() + ", placa " + placa;
			String descricao = " Abasteceu " + a.getQuantidadeLitros() + " de litros de " + a.getBomba().getCombustivel().getTipoCombustivel();
		
			AbastecimentoDTO dto = new AbastecimentoDTO();
			dto.setData(data);
			dto.setInfoCarro(infoCarro);
			dto.setPlaca(placa);
			dto.setDescricao(descricao);
			dto.setValorGasto( a.getValor() );
				
			if(a.getBomba().getCombustivel().getTipoCombustivel() == TipoCombustivel.ALCOOL ){
				dto.setQtdAlcool(a.getQuantidadeLitros());
				dto.setQtdGasolina( 0.0 );
			}else if(a.getBomba().getCombustivel().getTipoCombustivel() == TipoCombustivel.GASOLINAC ) {
				dto.setQtdAlcool( 0.0 );
				dto.setQtdGasolina(a.getQuantidadeLitros());
			}

			listaAbastecimentoDTO.add(dto);
		}
		
		return listaAbastecimentoDTO;
	}

}
