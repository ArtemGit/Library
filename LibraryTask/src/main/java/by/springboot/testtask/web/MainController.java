package by.springboot.testtask.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by Тёма on 22.02.2017.
 */
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView index() {
        ModelAndView model = new ModelAndView();
        model.setViewName("index");
        return  model;
    }

    @RequestMapping(value = "/books/view", method = RequestMethod.GET)
    public ModelAndView booksListHtml() {
        ModelAndView model = new ModelAndView();
        model.setViewName("books");
        return  model;
    }
    @RequestMapping(value = "/bookDetails/view", method = RequestMethod.GET)
    public ModelAndView bookDetailsHtml() {
        ModelAndView model = new ModelAndView();
        model.setViewName("book");
        return  model;
    }
    @RequestMapping(value = "/bookDetails/addBookForm/view", method = RequestMethod.GET)
    public ModelAndView getAddBookForm() {
        ModelAndView model = new ModelAndView();
        model.setViewName("addBookForm");
        return  model;
    }
    @RequestMapping(value = "/bookDetails/describePartForm/view", method = RequestMethod.GET)
    public ModelAndView getDescribePartForm() {
        ModelAndView model = new ModelAndView();
        model.setViewName("editDescribingPart");
        return  model;
    }

    @RequestMapping(value = "/bookDetails/updateContentForm/view", method = RequestMethod.GET)
    public ModelAndView getUpdateContentForm() {
        ModelAndView model = new ModelAndView();
        model.setViewName("updateContentForm");
        return  model;
    }
}
