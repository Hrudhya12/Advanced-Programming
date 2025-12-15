public class Cat extends Pet {
    public Cat(String name, int age, String gender, String personality,
               String breed, String color, String healthStatus,
               String favoriteActivity, double adoptionFee,
               double weight, String arrivalDate, String uniqueTrait) {
        super(name, age, gender, personality, breed, color, healthStatus,
                favoriteActivity, adoptionFee, weight, arrivalDate, uniqueTrait);
    }

    @Override
    public String getPetSound() {
        return "Meow!";
    }
}