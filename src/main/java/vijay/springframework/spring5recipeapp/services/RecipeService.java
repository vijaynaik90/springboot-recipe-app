package vijay.springframework.spring5recipeapp.services;

import vijay.springframework.spring5recipeapp.commands.RecipeCommand;
import vijay.springframework.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    public RecipeCommand saveRecipeCommand(RecipeCommand command);

    RecipeCommand findCommandById(Long l);
    public Recipe findById(Long l);

    public void deleteById(Long idToDelete);
}
