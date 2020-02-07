public class DragEnd extends EventoDiMouse {

  public DragEnd(int x, int y) {
    super(x,y);
  }
  
  public DragEnd(DragEnd e) {
	  super(e);
  }

  @Override
  public String toString() {
    return super.toString() + ", di tipo DragEnd";
  }

}
