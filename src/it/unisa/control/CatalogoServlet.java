package it.unisa.control;

import java.io.IOException; 
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.model.ProdottoBean;
import it.unisa.model.ProdottoDao;

@WebServlet("/catalogo")
public class CatalogoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		ProdottoDao prodDao = new ProdottoDao();
		ProdottoBean bean = new ProdottoBean();
		String sort = request.getParameter("sort");
		String action = request.getParameter("action");
		String redirectedPage = request.getParameter("page");;
	
		try {
            if (action != null) {
                if (action.equalsIgnoreCase("add")) {
                    bean.setNome(XSSSanitizer.sanitize(request.getParameter("nome")));
                    bean.setDescrizione(XSSSanitizer.sanitize(request.getParameter("descrizione")));
                    bean.setIva(XSSSanitizer.sanitize(request.getParameter("iva")));
                    bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                    bean.setQuantità(Integer.parseInt(request.getParameter("quantità")));
                    bean.setPiattaforma(XSSSanitizer.sanitize(request.getParameter("piattaforma")));
                    bean.setGenere(XSSSanitizer.sanitize(request.getParameter("genere")));
                    bean.setImmagine(XSSSanitizer.sanitize(request.getParameter("img")));
                    bean.setDataUscita(XSSSanitizer.sanitize(request.getParameter("dataUscita")));
                    bean.setDescrizioneDettagliata(XSSSanitizer.sanitize(request.getParameter("descDett")));
                    bean.setInVendita(true);
                    prodDao.doSave(bean);
                } else if (action.equalsIgnoreCase("modifica")) {
                    bean.setIdProdotto(Integer.parseInt(request.getParameter("id")));
                    bean.setNome(XSSSanitizer.sanitize(request.getParameter("nome")));
                    bean.setDescrizione(XSSSanitizer.sanitize(request.getParameter("descrizione")));
                    bean.setIva(XSSSanitizer.sanitize(request.getParameter("iva")));
                    bean.setPrezzo(Double.parseDouble(request.getParameter("prezzo")));
                    bean.setQuantità(Integer.parseInt(request.getParameter("quantità")));
                    bean.setPiattaforma(XSSSanitizer.sanitize(request.getParameter("piattaforma")));
                    bean.setGenere(XSSSanitizer.sanitize(request.getParameter("genere")));
                    bean.setImmagine(XSSSanitizer.sanitize(request.getParameter("img")));
                    bean.setDataUscita(XSSSanitizer.sanitize(request.getParameter("dataUscita")));
                    bean.setDescrizioneDettagliata(XSSSanitizer.sanitize(request.getParameter("descDett")));
                    bean.setInVendita(true);
                    prodDao.doUpdate(bean);
                }

				request.getSession().removeAttribute("categorie");

			}
			
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}


		try {
			request.getSession().removeAttribute("products");
			request.getSession().setAttribute("products", prodDao.doRetrieveAll(sort));
		} catch (SQLException e) {
			System.out.println("Error:" + e.getMessage());
		}
		
			
			response.sendRedirect(request.getContextPath() + "/" +redirectedPage);
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
