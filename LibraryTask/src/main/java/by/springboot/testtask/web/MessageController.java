package by.springboot.testtask.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MessageController {
    @RequestMapping(value = "/booksList/booksListEmpty", method = RequestMethod.GET)
    public ModelAndView getEmptyLibraryMessage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "В библиотеке нет книг!!");
        model.setViewName("warningMessage");
        return model;

    }

    @RequestMapping(value = "/booksList/404Eror", method = RequestMethod.GET)
    public ModelAndView getAddLibraryErrorMessage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Произошли ошибки в работе сервера!!");
        model.setViewName("errorMessage");
        return model;

    }

    @RequestMapping(value = "/book/SuccessDelete", method = RequestMethod.GET)
    public ModelAndView getSuccessDeleteBookMessage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Книга удалена из библиотеки!!");
        model.setViewName("successMessage");
        return model;
    }
        @RequestMapping(value = "/book/ErrorDelete", method = RequestMethod.GET)
        public ModelAndView getErrorDeleteBookMessage() {
            ModelAndView model = new ModelAndView();
            model.addObject("message", "Возникли ошибки при удалении!!");
            model.setViewName("errorMessage");
            return model;
        }

    @RequestMapping(value = "/book/ErrorDownload", method = RequestMethod.GET)
    public ModelAndView getErrorDownloadMessage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Возникли ошибки при скачивании!!");
        model.setViewName("errorMessage");
        return model;
    }

    @RequestMapping(value = "/book/updateSuccessMessage", method = RequestMethod.GET)
    public ModelAndView getUpdateSuccessMessag() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Базовая информация о книге обновлена!!");
        model.setViewName("successMessage");
        return model;
    }

    @RequestMapping(value = "/book/updateErrorMessage", method = RequestMethod.GET)
    public ModelAndView getUpdateErrorMessage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Возникли ошибки при обновлении базовой информации книги!!");
        model.setViewName("errorMessage");
        return model;
    }
    @RequestMapping(value = "/book/errorWatching", method = RequestMethod.GET)
    public ModelAndView getErrorWatchingMessage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Контент для данного журнала не был загружен!!");
        model.setViewName("errorMessage");
        return model;
    }
    @RequestMapping(value = "/book/filterErrorMessage", method = RequestMethod.GET)
    public ModelAndView getFilterErrorMessage() {
        ModelAndView model = new ModelAndView();
        model.addObject("message", "Некорректные параметры запроса для фильтра!!");
        model.setViewName("errorMessage");
        return model;
    }

    }