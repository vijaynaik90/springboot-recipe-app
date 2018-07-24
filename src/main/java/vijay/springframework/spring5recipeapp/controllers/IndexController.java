package vijay.springframework.spring5recipeapp.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import vijay.springframework.spring5recipeapp.domain.Category;
import vijay.springframework.spring5recipeapp.domain.UnitOfMeasure;
import vijay.springframework.spring5recipeapp.repositories.CategoryRepository;
import vijay.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;
import vijay.springframework.spring5recipeapp.services.RecipeService;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {

        model.addAttribute("recipes", recipeService.getRecipes());
        log.debug("Fetched Index page");

        return "index";
    }
}
