package dto;

public class House {

    public int number;
    protected String address;
    int floors;
    private String material;

    public void setMaterial(String material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "House{" +
                "number=" + number +
                ", address='" + address + '\'' +
                ", floors=" + floors +
                ", material='" + material + '\'' +
                '}';
    }
}
