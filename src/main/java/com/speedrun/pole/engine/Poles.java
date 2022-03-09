package com.speedrun.pole.engine;
import java.util.ArrayList;

public class Poles {
    public String[] series;
    protected String[] result;
    private int[] poles;
    private String[] codes;
    private ArrayList<Integer> stockage;
    private int total;
    private String[] code_last_stepok;
    private String[] code_beforeLast_stepok;
    private String[] code_last_stepchange;
    private String[] code_beforeLast_stepchange;
    private ArrayList<String> finalResult;

    public Poles(String first, String second, String third, String forth, String fifth, String sixth) {
        String[] res = new String[]{first, second, third, forth, fifth, sixth};
        this.finalResult = new ArrayList<String>();
        this.getResult(res);
    }

    public void getResult(String[] series) {
        this.series = series;
        this.poles = Constants.getPoles();
        this.codes = Constants.getCodes();
        String[] base = new String[this.poles.length + this.series.length];
        this.stockage = new ArrayList();

        int o;
        for(o = 0; o < this.poles.length; ++o) {
            int nombre = this.poles[o];
            if (this.series[0].equals(String.valueOf(nombre)) || this.series[0].equals("?")) {
            	
                for(int clef = 0; clef < this.series.length; ++clef) {
                    if (this.series[clef].equals(" ")) {
                        nombre = 99;
                    } else if(!this.series[clef].equals("?")){
                        nombre = Integer.parseInt(this.series[clef]);
                    } else if(this.poles.length > (o+clef) && this.series[clef].equals("?")){
                        nombre = this.poles[o + clef];
                    }
                    
                    if ((o+clef >= this.poles.length) || (nombre != this.poles[o + clef] && nombre != 99)) {
                        break;
                    }

                    base[o] = String.valueOf(o);

                    if (clef == 5) {
                        System.out.println("GOOD = " + Integer.parseInt(base[o]));
                        this.stockage.add(Integer.parseInt(base[o]));
                    }
                }
            }
        }

        this.total = 0;
        for(int i = 1; i < this.series.length; i++){
            if(!this.series[i].equals(" ")){
                this.total++;
            }
        }
        this.code_last_stepok = new String[this.stockage.size()];
        this.code_beforeLast_stepchange = new String[this.stockage.size()];
        this.code_beforeLast_stepok = new String[this.stockage.size()];
        this.code_last_stepchange = new String[this.stockage.size()];
        this.result = new String[this.stockage.size()];

        for(o = 0; o < this.stockage.size(); ++o) {
            int cle = (Integer) this.stockage.get(o) + this.total;
            if (cle > this.codes.length) {
                break;
            }
            if (this.codes.length > cle + 3) {
                this.code_last_stepok[o] = this.codes[cle];
                this.code_beforeLast_stepchange[o] = this.codes[cle + 2];
                this.code_beforeLast_stepok[o] = this.codes[cle + 1];
                this.code_last_stepchange[o] = this.codes[cle + 3];
                this.result[o] = this.code_last_stepok[o] + " " + this.code_beforeLast_stepchange[o] + " " + this.code_beforeLast_stepok[o] + " " + this.code_last_stepchange[o] + " " + (cle + 1);
                System.out.println("result for " + o + " is : " +this.result[o]);
            }
        }

        System.out.println("size : " + this.stockage.size());
        System.out.println(this.finalResult);
        if (this.stockage.size() == 0) {
            this.finalResult.add("nothing");
        } else {
            for(o = 0; o < this.stockage.size(); ++o) {
            	System.out.println("result for :" + o);
                System.out.println(this.result[o]);
                if(this.result[o] != null) {
                    this.finalResult.add(this.result[o]);
                }
            }
        }

    }

    public ArrayList<String> getRes() {
        return this.finalResult;
    }
}
