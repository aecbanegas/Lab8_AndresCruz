package lab8_andrescruz;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MBanegas
 */
public class Recorrido implements Runnable {

    private int tiempo;
    private JProgressBar pb;
    private Autobus bus;
    private ArrayList<Parada> pd;
    private ArrayList<Estudiante> est;
    private boolean puede;
    private boolean vive;
    private boolean parada;
    private Date fecha;
    private int cont = 0;
    private Parada actual;
    private int ante = 0;
    private JTable tabla;

    public Recorrido(JProgressBar pb, boolean puede, Autobus bus, ArrayList<Parada> pd, JTable tabla) {
        this.pb = pb;
        this.puede = puede;
        this.pd = pd;
        this.est = bus.getEstudiantes();
        this.tabla = tabla;
        this.bus = bus;
        fecha = new Date();
        vive = true;
        parada = true;
    }

    public void actuTabla() {
        tabla.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{
                    "Parada", "Tiempo", "Estudiante"
                }
        ));
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        for (int i = 0; i < est.size(); i++) {
            if (est.get(i).getParada().equals(actual)) {
                Object[] row = {est.get(i).getParada(), tiempo, est.get(i).getNombre()};
                modelo.addRow(row);
            }
        }
        tabla.setModel(modelo);
    }

    public double distancia(ArrayList<Parada> pd) {
        if (pd.size() != 1) {
            if (cont == 0) {
                int index = 0;
                Double menor = 999999999.9;
                for (int i = 0; i < pd.size(); i++) {
                    if (pd.get(i).getDistancia() < menor) {
                        index = i;
                        menor = pd.get(i).getDistancia();
                    }
                }
                cont++;
                actual = pd.get(index);
                ante = index;
                return actual.getDistancia();
            } else {
                ArrayList<Double> d = new ArrayList();
                int index = 0;
                Double menor = 999999999.9;
                for (int i = 0; i < pd.size(); i++) {
                    if (Math.sqrt(Math.pow(pd.get(i).getX() - actual.getX(), 2) + Math.pow(pd.get(i).getY() - actual.getY(), 2)) < menor && (Math.pow(pd.get(i).getX() - actual.getX(), 2) + Math.pow(pd.get(i).getY() - actual.getY(), 2)) != 0) {
                        menor = Math.sqrt(Math.pow(pd.get(i).getX() - actual.getX(), 2) + Math.pow(pd.get(i).getY() - actual.getY(), 2));
                        index = i;
                    }
                }
                actual = pd.get(index);
                pd.remove(ante);
                ante = index;                
                return menor;
            }
        } else {
            double menor = Math.sqrt(Math.pow(0 - actual.getX(), 2) + Math.pow(0 - actual.getY(), 2));
            actual = new Parada("UNITEC", 0, 0);
            return menor;
        }

    }

    public double tiempo(Autobus bus, double distancia) {
        double time = (distancia / bus.getVelocidad()) * 60;
        int max = (int) Math.round(time);
        tiempo = max;
        pb.setMaximum(max);
        pb.setString("" + actual.getNombre() + " faltan " + pb.getValue() + " minutos");
        pb.setValue(0);
        return time;
    }

    public Autobus getBus() {
        return bus;
    }

    public void setBus(Autobus bus) {
        this.bus = bus;
    }

    public ArrayList<Parada> getPd() {
        return pd;
    }

    public void setPd(ArrayList<Parada> pd) {
        this.pd = pd;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public boolean isVive() {
        return vive;
    }

    public void setVive(boolean vive) {
        this.vive = vive;
    }

    public boolean isParada() {
        return parada;
    }

    public void setParada(boolean parada) {
        this.parada = parada;
    }

    public JProgressBar getPb() {
        return pb;
    }

    public void setPb(JProgressBar pb) {
        this.pb = pb;
    }

    public boolean isPuede() {
        return puede;
    }

    public void setPuede(boolean puede) {
        this.puede = puede;
    }

    public ArrayList<Estudiante> getEst() {
        return est;
    }

    public void setEst(ArrayList<Estudiante> est) {
        this.est = est;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

    public Parada getActual() {
        return actual;
    }

    public void setActual(Parada actual) {
        this.actual = actual;
    }

    public int getAnte() {
        return ante;
    }

    public void setAnte(int ante) {
        this.ante = ante;
    }

    public JTable getTabla() {
        return tabla;
    }

    public void setTabla(JTable tabla) {
        this.tabla = tabla;
    }

    public void run() {
        double distancia = distancia(pd);
        double tiempo = tiempo(bus, distancia);
        while (vive) {
            if (puede) {
                if (parada) {
                    pb.setValue(pb.getValue() + 1);
                    pb.setString("" + actual.getNombre() + " faltan " + (pb.getMaximum() - pb.getValue()) + " minutos");
                    if (pb.getValue() == pb.getMaximum()) {
                        actuTabla();
                        parada = false;
                        JOptionPane.showMessageDialog(null, "Se completo el recorrido hacia " + actual.getNombre());
                        if (actual.getNombre().equals("UNITEC")) {
                            puede = false;
                            vive = false;
                        } else {
                            distancia = distancia(pd);
                            tiempo = tiempo(bus, distancia);
                            pb.setMaximum((int) Math.round(tiempo));                            
                            parada = true;
                        }
                    }
                }
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
        }
    }
}
