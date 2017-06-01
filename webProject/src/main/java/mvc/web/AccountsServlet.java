package mvc.web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.biz.AccountManagerEJB;
import mvc.model.AccountMVC;
import mvc.model.AccountAlreadyExistException;
import mvc.model.Amount;

//@WebServlet({"/accounts","/index.html"})
public class AccountsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AccountManagerEJB accountManager;

	public AccountsServlet() {

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		// res.setCharacterEncoding("UTF-8");

		try {

			Amount amount = new Amount(req.getParameter("accountBalanceInteger"), req.getParameter("accountBalanceFraction"));
			AccountMVC account = accountManager.save(req.getParameter("accountName"),
					req.getParameter("accountNumber"), amount);
			req.setAttribute("account", account);
			//getServletContext().getRequestDispatcher("/account.jsp").forward(req, res);
			//res.sendRedirect("account?accountNumber="+account.getNumber());
			res.sendRedirect(req.getContextPath()+"/account?accountNumber="+account.getNumber());

		} catch (NumberFormatException e) {
			req.setAttribute("error", "Le solde initial doit être un nombre ");
			doGet(req, res);
		} catch (AccountAlreadyExistException e) {
			req.setAttribute("error", "le compte existe déjà");
			doGet(req, res);
		}
		
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		getServletContext().getRequestDispatcher("/WEB-INF/jsp/createAccount.jsp").forward(req, res);
	}

}
