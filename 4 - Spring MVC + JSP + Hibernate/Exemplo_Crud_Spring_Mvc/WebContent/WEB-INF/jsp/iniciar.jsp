<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
<title>Cadastro de pessoa</title>
<script type="text/javascript" src="../resources/js/funcoes.js"></script>


<!-- Jquery -->
 <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/ui-lightness/jquery-ui.css">
  <script src="//code.jquery.com/jquery-1.10.2.js"></script>
  <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script> 

</head>
<body>
<c:set var="estadoselecionado" scope="request" value="${estadoselecionado}"/>
	<form action="salvar" method="post" id="formPessoa">

		<label for="nome">Nome</label>	
		<input type="text" id="nome" name="nome" value="${pessoa.nome}">
		
		<br/>
		
		<label for="endereco">Endereço</label>
		<input type="text" id="endereco" name="endereco" value="${pessoa.endereco}">
		
		<br/>
		<label for="estado">Estado</label>
		
		<select id="estado" name="estado.id" onchange="consultarCidades();">
		   <option value="">--[Selecione]--</option>
		   <c:forEach items="${estados}" var="estado">
		   
		      <c:choose>
		       
		       <c:when test="${estado.id == estadoselecionado}">
		          <option value="${estado.id}" selected="selected" id="${estado.id}">${estado.nome}</option>
		       </c:when>
		      
		       <c:otherwise>
		        <option value="${estado.id}" id="${estado.id}">${estado.nome}</option>
		       </c:otherwise>
		      
		      </c:choose> 
		      
		     
		   </c:forEach>
		</select>
		
		
		<br/>
		<label for="cidades">Cidade</label>
		
		<select id="cidades" name="cidades.id" >
		   <option value="">--[Selecione]--</option>
		   <c:forEach items="${cidades}" var="cidade">
		     <option value="${cidade.id}" id="${cidade.id}">${cidade.nome}</option>
		   </c:forEach>
		</select>
		
		
		
		<br/>
		<label for="fone">Telefone</label>
		<input type="text" id="fone" name="fone"><button onclick="addFone();" type="button">Adicionar</button>
		
		<div id="fones"></div>				
		
		<br/>
		<input type="submit" value="Salvar">
		 
		<input type="hidden" id="telefonePessoasTemp" 
		 name="telefonePessoasTemp" >
		 
	</form>
	
	<script type="text/javascript">
	  function consultarCidades() {
		 document.getElementById("formPessoa").action = 'cidades';
		 document.getElementById("formPessoa").submit();
	  }
	</script>
</body>
</html>