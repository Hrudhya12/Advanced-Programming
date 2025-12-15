import java.time.LocalDate;

public class Pet {
    protected String name;
    protected int age;
    protected String gender;        // Male/Female
    protected String personality;
    protected boolean isAdopted = false;

    // Extra details
    protected String breed;
    protected String color;
    protected String healthStatus;
    protected String favoriteActivity;
    protected double adoptionFee;   // in euros (€)
    protected double weight;        // in kg
    protected String arrivalDate;
    protected String uniqueTrait;

    // Adoption tracking
    protected String adopterUsername = null;
    protected LocalDate adoptionDate = null;

    public Pet(String name, int age, String gender, String personality,
               String breed, String color, String healthStatus,
               String favoriteActivity, double adoptionFee,
               double weight, String arrivalDate, String uniqueTrait) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.personality = personality;
        this.breed = breed;
        this.color = color;
        this.healthStatus = healthStatus;
        this.favoriteActivity = favoriteActivity;
        this.adoptionFee = adoptionFee;
        this.weight = weight;
        this.arrivalDate = arrivalDate;
        this.uniqueTrait = uniqueTrait;
    }

    public String getListLine() {
        return name + " | Age: " + age + " | Gender: " + gender + " | Breed: " + breed;
    }

    public String getFullDetails() {
        String status;
        if (isAdopted) {
            status = "Adopted by " + adopterUsername + " on " + adoptionDate;
        } else {
            status = "Available";
        }
        return "Name: " + name +
                "\nAge: " + age + " years" +
                "\nGender: " + gender +
                "\nPersonality: " + personality +
                "\nBreed: " + breed +
                "\nColor: " + color +
                "\nHealth Status: " + healthStatus +
                "\nFavorite Activity: " + favoriteActivity +
                "\nAdoption Fee: €" + adoptionFee +
                "\nWeight: " + weight + " kg" +
                "\nArrival Date: " + arrivalDate +
                "\nUnique Trait: " + uniqueTrait +
                "\nSound: " + getPetSound() +
                "\nStatus: " + status;
    }

    public String getPetSound() {
        return "Some generic sound";
    }

    public void adopt(String username) {
        isAdopted = true;
        adopterUsername = username;
        adoptionDate = LocalDate.now();
    }

    public boolean isAdopted() {
        return isAdopted;
    }

    public String getAdopterUsername() {
        return adopterUsername;
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate;
    }
}