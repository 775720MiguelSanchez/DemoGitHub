package Vista;

import javax.swing.JLabel;


public class FichaVista extends JLabel{
    private int id;
    private String color;

    public FichaVista(int id, String color){
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
