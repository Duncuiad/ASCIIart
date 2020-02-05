public abstract class Strumento {

  private Canvas canvas;

  public Strumento(Canvas canvas) {
    this.canvas = canvas;
  }

  public abstract void ricevi(EventoDiMouse e);
}
