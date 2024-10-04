package com.tictactoe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name="LogicServlet", value="/logic")
public class LogicServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException{
        //Получаем текущую сессию
        HttpSession currentSession = req.getSession();
        //Получаем обуъект поля
        Field field = extractField(currentSession);

        //Получаем индекс ячейки
        int index = getSelectedIngex(req);
        Sign currentSign = field.getField().get(index);

        // Проверяем, что ячейка, по которой был клик пустая.
        // Иначе ничего не делаем и отправляем пользователя на ту же страницу без изменений
        // параметров в сессии
        if (Sign.EMPTY != currentSign) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.jsp");
            dispatcher.forward(req, resp);
            return;
        }

        //Ставим крестик в ячейки, по которой произошел клик
        field.getField().put(index, Sign.CROSS);

        //Проверка победил ли пользователь после добавления крестика
        if (checkWin(resp, currentSession, field)) {
            return;
        }

        //Получаем индекс пустой ячейки
        int indexEmpty = field.getEmptyFieldIndex();

        if (indexEmpty >= 0){
            field.getField().put(indexEmpty, Sign.NOUGHT);
            //Проверка победил ли нолик, после добавления последнего нолика
            if (checkWin(resp, currentSession, field)) {
                return;
            }
        }else{
            //Добавим новый аттрибут в сессию при ничье
            currentSession.setAttribute("draw", true);
        }


        //Считаем список значений
        List<Sign> data = field.getFieldData();

        //Обновляем объекты поля и список значков в сессии
        currentSession.setAttribute("data", data);
        currentSession.setAttribute("field", field);

        //Отправляем ответ на страницу
        resp.sendRedirect("/index.jsp");
    }

    private Field extractField(HttpSession currentSession){
        Object fieldAttribute = currentSession.getAttribute("field");
        try{
            return (Field) fieldAttribute;
        }catch (RuntimeException e){
            currentSession.invalidate();
            throw new RuntimeException("Session is broken, try one more time");
        }
    }

    private int getSelectedIngex(HttpServletRequest request){
        String click = request.getParameter("click");
        boolean isNumeric = click.chars().allMatch(Character::isDigit);
        return isNumeric ? Integer.parseInt(click):0;
    }

    /**
     * Метод проверяет, нет ли трех крестиков/ноликов в ряд.
     * Возвращает true/false
     */
    private boolean checkWin(HttpServletResponse response, HttpSession currentSession, Field field) throws IOException {
        Sign winner = field.checkWin();
        if (winner == Sign.CROSS || winner == Sign.NOUGHT){
            //Добавляем победителя
            currentSession.setAttribute("winner", winner);

            //Считаем список значений
            List<Sign> data = field.getFieldData();

            // Обновляем этот список в сессии
            currentSession.setAttribute("data", data);

            // Шлем редирект
            response.sendRedirect("/index.jsp");
            return true;
        }

        return false;
    }
}
