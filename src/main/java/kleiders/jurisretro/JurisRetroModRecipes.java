package kleiders.jurisretro;

import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.Item;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.helper.RecipeBuilder;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShaped;
import turniplabs.halplibe.helper.recipeBuilders.RecipeBuilderShapeless;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class JurisRetroModRecipes implements RecipeEntrypoint {

	public void initializeRecipes() {
		RecipeBuilderShaped simpleAdditionRecipe = new RecipeBuilderShaped(JurisRetroMod.MOD_ID, "XY");
		simpleAdditionRecipe.addInput('X', Item.paper).addInput('Y', Item.jar).create("airSpell", new ItemStack(JurisRetroModItems.air_spell, 1));
		simpleAdditionRecipe.addInput('X', Item.paper).addInput('Y', JurisRetroModItems.chicken_pig_egg).create("transformationSpell", new ItemStack(JurisRetroModItems.transformation_spell, 1));
		RecipeBuilderShaped bootsRecipe = new RecipeBuilderShaped(JurisRetroMod.MOD_ID).setShape("X X", "X X");
		bootsRecipe.addInput('X', JurisRetroModItems.spider_mother_leather).create("spiderBoots", new ItemStack(JurisRetroModItems.spider_boots, 1));
	}

	@Override
	public void onRecipesReady() {
		initializeRecipes();
	}

	@Override
	public void initNamespaces() {
		RecipeBuilder.initNameSpace(JurisRetroMod.MOD_ID);
		RecipeBuilder.getRecipeNamespace(JurisRetroMod.MOD_ID);
	}
}
