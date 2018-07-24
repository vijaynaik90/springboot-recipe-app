package vijay.springframework.spring5recipeapp.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;
    @Before
    public void setUp(){
        category  = new Category();
    }

    @Test
    public void getId()throws Exception{
        Long idVal = 4L;


        category.setId(idVal);
        assertEquals(idVal,category.getId());
    }

    @Test
    public void getDescription() throws Exception {
    }

    @Test
    public void getRecipes() throws Exception {
    }
}
