package engine.process;

import engine.map.Case;

public class Coup {

    public Case depart;
    public Case arrivee;

    public Coup(Case d, Case a) {
        depart = d;
        arrivee = a;
    }
}
