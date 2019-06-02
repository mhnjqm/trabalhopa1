<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% URL contexto = new URL(
            //            "https",
            request.getScheme(),
            request.getServerName(),
            request.getServerPort(),
            request.getContextPath());%>

<!DOCTYPE html>
<html>
    <head>
        <script>var contexto = "<%= contexto%>/";</script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar medidores</title>
        <link rel="stylesheet" type="text/css" href="<%= contexto%>/css/pagina2.css"/>
        <script type="text/javascript" src="<%= contexto %>/js/pagina2.js"></script>
    </head>

    <body>
        <h1>Home Automation</h1>
        
        <div id="dialogo">
        </div>
        
        <br>
        
        
        
        
        <div id="divMedidoresCadastrados">
            <h3>Medidores cadastrados</h3>
            <h4><a href='?nomeDoTratadorDePagina=mvc.Tratador_pagina2_jsp&action=preCadastro' style="color:#0044ff">Cadastrar novo medidor</a></h4>
            
            
            
            
            <table id="tabelamedidores">
                <tr>
                    <td>Nome</td>
                    <td>Tabela</td>
                    <td>&nbsp;</td>
                </tr>
                
                <% if (request.getSession().getAttribute("toInsertMeasurer") == "1") { %>
                <form id="CadastroMedidor" method="POST" action="controller">
                    <input type="hidden"
                           name="nomeDoTratadorDePagina"
                           value="mvc.pagehandlers.Tratador_pagina2_jsp" />
                    <input type="hidden"
                           name="menu"
                           value="cadastrar" />    
                    
                    <tr>
                        <td><input type=text name="name" value=""/></td>
                        <td><input type=text name="table" value="" readonly/></td>
                        <td>
                            <a href="#" onclick="submitForm('CadastroMedidor');">
                                Cadastrar
                            </a>
                            /
                            <a href='?nomeDoTratadorDePagina=mvc.Tratador_pagina2_jsp'>Cancelar</a>
                        </td>
                    </tr>
                </form>
                    
                    
                    
                    <tr>
                        <td><input type=text name="name" value=""/></td>
                        <td><input type=text name="table" value="" readonly/></td>
                        <td>
                            <a href="#" onclick="submitForm('CadastroMedidor');">
                                Cadastrar
                            </a>
                            
                            <a href='?pageHandlerName=mvc.pagehandlers.Handler_pagina2_jsp'>Cancelar</a>
                        </td>
                    </tr>
                </form>
                <% }%>
                
                
                
                
                
                
                
                
                
                asdassasdasdfffff
                
                
                
                
                
            </table>
        </div>
        
        
    </body>
</html>
