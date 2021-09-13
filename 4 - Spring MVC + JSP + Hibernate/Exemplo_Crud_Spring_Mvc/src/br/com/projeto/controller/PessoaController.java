package br.com.projeto.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.projeto.classes.Cidades;
import br.com.projeto.classes.Estados;
import br.com.projeto.classes.Pessoa;
import br.com.projeto.classes.PessoaBean;
import br.com.projeto.classes.TelefonePessoa;
import br.com.projeto.classes.TelefonePessoaBean;
import br.com.projeto.crud.ImplementacaoCrud;

import com.google.gson.Gson;

@Controller
@RequestMapping(value="/pessoa")
public class PessoaController extends ImplementacaoCrud<Pessoa>{


	@RequestMapping(value="salvar", method = RequestMethod.POST)
	public String salvar(Model model, Pessoa pessoa) throws Exception {

		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{");
		stringBuilder.append("\"id\":").append("\""+(pessoa.getId() != null ? pessoa.getId() : "")+"\",");
		stringBuilder.append("\"nome\":").append("\""+pessoa.getNome()+"\",");
		stringBuilder.append("\"endereco\":").append("\""+pessoa.getEndereco()+"\",");
		stringBuilder.append("\"telefonePessoas\":[").append(pessoa.getTelefonePessoasTemp()+"]");
		stringBuilder.append("}");
		
		PessoaBean  pessoaBean = new Gson().fromJson(stringBuilder.toString(), PessoaBean.class);
		
		for (TelefonePessoaBean bean : pessoaBean.getTelefonePessoas()) {
			TelefonePessoa telefonePessoa = new TelefonePessoa();
			telefonePessoa.setNumero(bean.getNumero());
			telefonePessoa.setPessoa(pessoa);
			
			pessoa.getTelefonePessoas().add(telefonePessoa);
		}
		
		pessoa = super.merge(pessoa);
		model.addAttribute("pessoa", pessoa);
 		return "pessoasalva";
	}
	
	
	@RequestMapping(value = "cidades", method = RequestMethod.POST)
	public String carregarCidades (Model modelInView, Pessoa pessoa){
		
		try {
			
		List<Estados> estados = (List<Estados>) super.finListQuery("from Estados"); 
		modelInView.addAttribute("estados", estados);
		
		List<Cidades> cidades = (List<Cidades>) super.
				finListQuery("from Cidades where estados.id = " + pessoa.getEstado().getId());
		
		modelInView.addAttribute("cidades", cidades);
		modelInView.addAttribute("estadoselecionado", pessoa.getEstado().getId());
		modelInView.addAttribute("pessoa", pessoa);
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return "iniciar"; 
				
	}

	
	
	@RequestMapping(value="iniciar", method = RequestMethod.POST)
	public String iniciar(Model model) {
		try {
		
			List<Estados> estados = (List<Estados>) super.finListQuery("from Estados"); 
			model.addAttribute("estados", estados);
		
		}catch (Exception e){
			e.printStackTrace();
		}
		
		return "iniciar";
	}

}
