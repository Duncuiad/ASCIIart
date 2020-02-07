public class MouseMove extends EventoDiMouse {

  public MouseMove(int x, int y) {
    super(x,y);
  }
  
  public MouseMove(MouseMove e) {
	  super(e);
  }

  @Override
  public String toString() {
    return super.toString() + ", di tipo MouseMove";
  }

}
