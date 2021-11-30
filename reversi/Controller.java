package reversi;

import javax.swing.event.MouseInputAdapter;

public class Controller extends MouseInputAdapter{
    public Model model;
    public View view;

    public Controller(Model aModel){
        model = aModel;
        return;
    }
}
