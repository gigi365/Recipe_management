import java.util.*;

public class Recipes {
	public static ArrayList<Recipes> recipes = new ArrayList<Recipes>();
	public static Recipes defaultRecipe = new Recipes("Eggs Woodhouse", "creamed spinach, bechamel, hollandaise, poached eggs, artichoke bottoms, Pata Negra ham, Perigold truffle, Beluga caviar, paprika, Kashmiri saffron", "All ingredients are a must here!", "Warm artichoke bottoms, slices of ham, and Perigold truffle each in separate pans (More specifically, a copper-bottomed saute pan). Chiffonade the Pata Negra ham. Slice the truffle exceedingly thin. Then place the creamed spinach on a warmed plate. Over the spinach, place the artichoke bottoms each with a poached egg on top of them. Sprinkle the Pata Negra ham chiffonade and truffle slices over the dish. Then ladle on the Hollandaise sauce. To top it off, garnish with paprika, Beluga caviar, and Kashmiri saffron.", 30, 99999);
	
	public static int totalRecipes;
	private int id;
	private String name;
	private ArrayList<String> mainIngredients;
	private ArrayList<String> optionalIngredients;
	private String description;
	private int timeInMin;
	public int rating;

	public Recipes(String name, String mainIngredients, String optionalIngredients, String desc, int time, int rating) {
		// turn mainIngredients string into an array of ingredients
		editMainIngredients(mainIngredients);

		// turn optionalIngredients to array of ingredients
		editOptionalIngredients(optionalIngredients);
		
		totalRecipes ++;
		this.id = totalRecipes;
		editName(name);
		editDesc(desc);
		editTime(time);
		editRating(rating);

		recipes.add(this);
	}

	public void displayRecipe() {
		System.out.println("Name: " + this.getName());
		System.out.println("Main Ingredients: " + this.getMainIngredients().toString());
		System.out.println("Optional Ingredients: " + this.getOptionalIngredients().toString());
		System.out.println("Description: " + this.getDesc());
		System.out.println("Cooking time (mins): " + this.getTime());
		System.out.println("Rating(1-5): " + this.getRating());
	}

	public static ArrayList<Recipes> getSortByTime(int time) {
		// sort by time
		Collections.sort(recipes, new timeRatingComparator());
		Collections.reverse(recipes);
		
		// pick out 3 closest to time given w/ highest rating
		ArrayList<Recipes> sorted_list = new ArrayList<Recipes>();
		int i = 0;
		while(sorted_list.size() < 3) {
			if (recipes.get(i).getTime() <= time) sorted_list.add(recipes.get(i));
			i++;
			if (i >= recipes.size()) break;
		}

		return sorted_list;
	}

	public int getID() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public ArrayList<String> getMainIngredients() {
		return this.mainIngredients;
	}

	public ArrayList<String> getOptionalIngredients() {
		return this.optionalIngredients;
	}

	public String getDesc() {
		return this.description;
	}

	public int getTime() {
		return this.timeInMin;
	}

	public int getRating() {
		return this.rating;
	}

	public void editName(String name) {
		this.name = name;
	}

	public void editDesc(String desc) {
		this.description = desc;
	}

	public void editTime(int time) {
		this.timeInMin = time;
	}

	public void editRating(int rating) {
		this.rating = rating;
	}

	public void editMainIngredients(String mainIngredients) {
		this.mainIngredients = new ArrayList<String>();
		int startIndex = 0;
		for (int i = 0; i < mainIngredients.length(); i ++) {
			if (mainIngredients.charAt(i) == ',' || i+1 == mainIngredients.length()) {
				while(startIndex < mainIngredients.length() && mainIngredients.charAt(startIndex) == ' ') startIndex++;
				int endIndex = i-1;
				if (i+1 == mainIngredients.length()) endIndex++;
				while(mainIngredients.charAt(endIndex) == ' ') endIndex--;
				this.mainIngredients.add(mainIngredients.substring(startIndex, endIndex+1));
				startIndex = i+1;
			}
		}
	}

	public void editOptionalIngredients(String optionalIngredients) {
		this.optionalIngredients = new ArrayList<String>();
		int startIndex = 0;
		for (int i = 0; i < optionalIngredients.length(); i ++) {
			if (optionalIngredients.charAt(i) == ',' || i+1 == optionalIngredients.length()) {
				while(startIndex < optionalIngredients.length() && optionalIngredients.charAt(startIndex) == ' ') startIndex++;
				int endIndex = i-1;
				if (i+1 == optionalIngredients.length()) endIndex++;
				while(optionalIngredients.charAt(endIndex) == ' ') endIndex--;
				this.optionalIngredients.add(optionalIngredients.substring(startIndex, endIndex+1));
				startIndex = i+1;
			}
		}
	}
}

class timeRatingComparator implements Comparator<Recipes> {
	@Override
	public int compare(Recipes recipe1, Recipes recipe2) {
		int value = Integer.compare(recipe1.getTime(), recipe2.getTime());
		if (value == 0) {
			return Integer.compare(recipe1.getRating(), recipe2.getRating());
		} else {
			return value;
		}
	}
}

