import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mvc.model.Medidas;
import mvc.model.Medidores;
import mvc.controller.IFTratadorDePaginas;
import DAO.databaseDAO;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Tratador_pagina2_jsp implements IFTratadorDePaginas {
    @Override
    public String processar(HttpServletRequest request, HttpServletResponse response){
        String jspURL = "pagina2.jsp";
        
        request.getSession().removeAttribute("toInsertMeasurer");
        request.getSession().removeAttribute("editableMeasurer");
        
        String menu = request.getParameter("menu");
        
        // evitar erro de nullpointer
        if(menu == null) menu = "";
        
        switch(menu){
            case "editCadstrar":
                request.getSession().setAttribute("cadastrar_medidor", "1");
                listaDeMedidores(request);
                break;
            case "cadastrar":
                cadastrarMedidor(request);
                listaDeMedidores(request);
                break;
            case "editEditar":
                request.getSession().setAttribute("editar_medidor", request.getParameter("serialno"));
                listaDeMedidores(request);
                break;
            case "edit":
                editarMedidor(request);
                listaDeMedidores(request);
                break;        
            case "delete":
                excluirMedidor(request);
                listaDeMedidores(request);
                break;
            default:
                listaDeMedidores(request);
        }
        return jspURL;
    }    
    
    private void listaDeMedidores(HttpServletRequest request) {
        databaseDAO db = new databaseDAO(); 
        ArrayList<Medidores> medidores = db.doReadMedidores();
        request.getSession().setAttribute("medidores", medidores);
    }
    
    private void cadastrarMedidor(HttpServletRequest request){
        databaseDAO db = new databaseDAO();
        String medidor_nome = request.getParameter("nome");
        String tabela = request.getParameter("tabela"); //nome da tabela (auto)
        db.doCreate(medidor_nome, tabela);
    }    
    
    private void editarMedidor(HttpServletRequest request) {
        databaseDAO db = new databaseDAO();
        String medidor_serialno = request.getParameter("serialno");
        String medidor_nome = request.getParameter("nome");
        db.doUpdate(medidor_serialno, medidor_nome);
    }

    private void excluirMedidor(HttpServletRequest request) {
        databaseDAO db = new databaseDAO();
        String medidor_serialno = request.getParameter("serialno");
        db.doDelete(medidor_serialno);
    }    
    
}
