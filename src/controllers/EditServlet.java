package controllers;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Task;
import utils.DBUtil;

/**
 * Servlet implementation class EditServlet
 */
@WebServlet("/edit")
public class EditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        接続
        EntityManager em = DBUtil.createEntityManager();
//        編集したいidに一致するタスクを取得
        Task t = em.find(Task.class, Integer.parseInt(request.getParameter("id")));
//        切断
        em.close();
//        タスクとセッションIDをリクエストスコープに保管
        request.setAttribute("task", t);
        request.setAttribute("_token", request.getSession().getId());
//        タスクIDをセッションスコープに保管
        request.getSession().setAttribute("task_id", t.getId());
//        JSPへフォワード
        RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/views/tasks/edit.jsp");
        rd.forward(request, response);
    }
}