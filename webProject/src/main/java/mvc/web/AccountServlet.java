package mvc.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.biz.AccountManagerEJB;
import mvc.model.Account;
import mvc.model.AccountDoesNotExistException;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/account")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private @EJB AccountManagerEJB accountManager;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
		super();
	}


	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		try {
			Account account = accountManager.getByNumber(req.getParameter("accountNumber"));
			req.setAttribute("account", account);
			req.getServletContext().getRequestDispatcher("/WEB-INF/jsp/account.jsp").forward(req, res);
		} catch (AccountDoesNotExistException e) {
			log(e.getMessage(), e);
			res.sendError(HttpServletResponse.SC_NOT_FOUND);
		}

	}

}
