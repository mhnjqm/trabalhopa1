import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.model.Medidas;
import mvc.model.Medidores;
import mvc.controller.IFTratadorDePaginas;
import DAO.databaseDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
    
public class Tratador_pagina1_jsp implements IFTratadorDePaginas{
    @Override
    public String processar(HttpServletRequest request, HttpServletResponse response){
        String jspURL = "pagina1.jsp";
        String menu = request.getParameter("menu");
        switch(menu){
                case "carrega_medidor":
                    String medidor_nome = request.getParameter("selectVariaveis");
                    String periodo = request.getParameter("selectPeriodo");
                    DateTimeFormatter formatodata = DateTimeFormatter.ofPattern("dd/mm/yyyy");
                    LocalDate datafinal = LocalDate.parse(request.getParameter("datafinal"), formatodata);
                    
                    listaDeMedidas(request, medidor_nome , datafinal); 

                    request.getSession().setAttribute("selectVariaveis", medidor_nome);
                    request.getSession().setAttribute("selectPeriodo", periodo);
                    request.getSession().setAttribute("datafinal", datafinal);  

                    listaDeMedidores(request);
                    break;
                
                default:         
                    listaDeMedidores(request);
                    //request.getSession().removeAttribute("medidas"); 
                
        }

        return jspURL;
    }
    
    
    
    private void listaDeMedidores(HttpServletRequest request) {
        databaseDAO db = new databaseDAO(); 
        ArrayList<Medidores> medidores = db.doReadMedidores();
        request.getSession().setAttribute("medidores", medidores);
    }
    
    private void listaDeMedidas(HttpServletRequest request, String medidor_nome, LocalDate datafinal) {
        databaseDAO db = new databaseDAO(); 
        ArrayList<Medidas> medidas = db.doReadMedidas(medidor_nome, datafinal);
        request.getSession().setAttribute("medidas", medidas);
    }
    
}
