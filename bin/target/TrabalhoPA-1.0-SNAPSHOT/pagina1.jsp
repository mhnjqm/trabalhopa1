<%@page import="java.net.URL"%>
<%@page import="mvc.model.Medidas"%>
<%@page import="mvc.model.Medidores"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDate"%>

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
        <title>Selecionar medidor</title>
        <link rel="stylesheet" type="text/css" href="<%= contexto%>/css/pagina1.css"/>
        <script type="text/javascript" src="<%= contexto %>/js/pagina1.js"></script>
    </head>

    <body>
        <h1>Home Automation</h1>
        <!-- page handler bah-->

        <div id="dialogo">
        </div>
        
        <form method="GET" action="controller" id="form1">
            <input type="hidden"
                   name="nomeDoTratadorDePagina"
                   value="mvc.pagehandlers.Tratador_pagina1_jsp" />
            <input type="hidden"
                   name="menu"
                   value="case" />
        
        <div id="divComandos">
            
            
            <div class="caixas_menu">
                MEDIDOR
                <select id="selectVariaveisDashboard" name="selectVariaveis">
                    <% 
                        ArrayList<Medidores> medidores = (ArrayList<Medidores>) request.getSession().getAttribute("medidores");
                        String editableMeasurer = (String) request.getSession().getAttribute("editableMedidas");

                        String medidor_serialno = (String) request.getSession().getAttribute("selectVariaveis");
                        for (Medidas medida : medidores) {
                    %>
                    <option value="<% out.print(measurer.getserialno_medidor()); %>"
                            <% if(measurer.getserialno_medidor().equals(medidor_serialno)) { %>selected<% } %>
                        >
                        <% out.print(measurer.getName()); %>
                    </option>
                    <% } %>
                </select>
            </div>
                
                
            <div class="caixas_menu">
                PERÍODO
                <select id="selectPeriodo" name="selectPeriodo">
                    <option value="diario">DIÁRIO</option>
                    <option value="semanal">SEMANAL</option>
                    <option value="mensal">MENSAL</option>
                    <option value="anual">ANUAL</option>
                </select>
            </div>
                
            <div class="caixas_menu">
                DATA FINAL
                    <%
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/mm/yyyy");
                        LocalDate datafinal = (LocalDate) request.getSession().getAttribute("datafinal");
                        if(datafinal == null) datafinal = LocalDate.now();
                    %>
                    <input id="datafinal" name="endDate" type="text" value="<% out.print(datafinal.format(formatter)); %>"/>
                </div>
                
            <div class="caixas_menu">
                <span id="labelGrafico">GRÁFICO</span>/<span id="labelTabela" 
                      style="color:lightgrey;">TABELA</span>
                <div id="divextslider">
                    <div id="posGrafico">
                    </div>
                    <div id="posTabela">
                    </div>
                </div>
            </div>
            <div class="caixas_menu">
                <br>
                <a href="#" style="color:#0044ff;">
                    LER/RELER
                </a>
            </div>
        </div>

        <div id="divGraficoTabela">
        </div>
    </body>
</html>
