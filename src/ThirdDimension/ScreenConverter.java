package ThirdDimension;
import Math.Vector3;

public class ScreenConverter {
    private double xr, yr, wr, hr;
    private int ws, hs;

    public ScreenPoint r2s(Vector3 p){
        int i=(int)((p.getX()-xr)*ws/wr);
        int j=(int)((yr-p.getY())*hs/hr);
        return new ScreenPoint(i,j);
    }

    public ScreenConverter(double xr, double yr, double wr, double hr, int ws, int hs) {
        this.xr = xr;
        this.yr = yr;
        this.wr = wr;
        this.hr = hr;
        this.ws = ws;
        this.hs = hs;
    }

    public void zoomIn(){
        if(getWr() <= 0.05 || getHr() <= 0.05) return;
//        zoom *= 0.9;
        double offsetX = getWr()*0.05;
        double offsetY = getHr()*0.05;
        setWr(getWr()*0.9);
        setHr(getHr()*0.9);
        setXr(getXr() + offsetX);
        setYr(getYr() - offsetY);
    }
    public void zoomOut(){
        if(getWr() >= 30000 || getHr() >= 30000)return;
//        zoom *= 1.1;
        double offsetX = getWr()*0.05;
        double offsetY = getHr()*0.05;
        setWr(getWr()*1.1);
        setHr(getHr()*1.1);
        setXr(getXr() - offsetX);
        setYr(getYr() + offsetY);
    }

    public double getXr() {
        return xr;
    }

    public double getYr() {
        return yr;
    }

    public double getWr() {
        return wr;
    }

    public double getHr() {
        return hr;
    }

    public int getWs() {
        return ws;
    }

    public int getHs() {
        return hs;
    }

    public void setXr(double xr) {
        this.xr = xr;
    }

    public void setYr(double yr) {
        this.yr = yr;
    }

    public void setWr(double wr) {
        this.wr = wr;
    }

    public void setHr(double hr) {
        this.hr = hr;
    }

    public void setWs(int ws) {
        this.ws = ws;
    }

    public void setHs(int hs) {
        this.hs = hs;
    }
}
