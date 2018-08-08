package vijay.springframework.spring5recipeapp.services;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import vijay.springframework.spring5recipeapp.commands.IngredientCommand;
import vijay.springframework.spring5recipeapp.converters.IngredientCommandToIngredient;
import vijay.springframework.spring5recipeapp.converters.IngredientToIngredientCommand;
import vijay.springframework.spring5recipeapp.converters.UnitOfMeasureCommandToUnitOfMeasure;
import vijay.springframework.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import vijay.springframework.spring5recipeapp.domain.Ingredient;
import vijay.springframework.spring5recipeapp.domain.Recipe;
import vijay.springframework.spring5recipeapp.repositories.RecipeRepository;
import vijay.springframework.spring5recipeapp.repositories.UnitOfMeasureRepository;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class IngredientServiceImplTest {

    private final IngredientCommandToIngredient ingredientCommandToIngredient;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    IngredientService ingredientService;

    public IngredientServiceImplTest(){
        ingredientCommandToIngredient = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
        ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
    }

    @Before
    public void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);

        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand,ingredientCommandToIngredient,recipeRepository,unitOfMeasureRepository);
    }


    @Test
    public void testFindByRecipeIdAndIngredientId() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3 = new Ingredient();
        ingredient3.setId(3L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);
        recipe.addIngredient(ingredient3);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

         when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Recipe recipe1 = recipeOptional.get();

       IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipe1.getId(),2L);

       assertEquals(Long.valueOf(2L),ingredientCommand.getId());
       assertEquals(Long.valueOf(1L),ingredientCommand.getRecipeId());
       verify(recipeRepository,times(1)).findById(anyLong());

    }

    @Test
    public void testSaveIngredientCommand()throws  Exception{

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(123L);
        ingredientCommand.setId(456L);

        Optional<Recipe> recipeOptional = Optional.of(new Recipe());

        Recipe savedRecipe = new Recipe();
        savedRecipe.addIngredient(new Ingredient());
        savedRecipe.getIngredients().iterator().next().setId(456L);
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any())).thenReturn(savedRecipe);

        IngredientCommand ingredientCommandReturned = ingredientService.saveIngredientCommand(ingredientCommand);

        assertEquals(Long.valueOf(456),ingredientCommandReturned.getId());
        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));
    }

    @Test
    public void testDeleteIngredientById() throws Exception{
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient = new Ingredient();
        ingredient.setId(2L);

        recipe.addIngredient(ingredient);
        ingredient.setRecipe(recipe);

        Recipe savedRecipe = new Recipe();
        savedRecipe.setId(1L);

        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);
        when(recipeRepository.save(any(Recipe.class))).thenReturn(savedRecipe);

        ingredientService.deleteById(1L,2L);

        verify(recipeRepository,times(1)).findById(anyLong());
        verify(recipeRepository,times(1)).save(any(Recipe.class));
    }




}
