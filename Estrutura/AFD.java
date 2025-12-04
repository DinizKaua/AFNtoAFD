package Estrutura;

import java.util.*;

public class AFD {
    private final Set<Estado> conjunto;
    
    public AFD(Set<Estado> conjunto){
        if(conjunto == null){
            this.conjunto = new HashSet<>();
        }
        else{
            this.conjunto = new HashSet<>(conjunto);
        }
    }

    // verifica se algum estado é final
    public boolean possuiFinal() {
        for (Estado e : conjunto) {
            if (e.getEhFinal()) return true;
        }
        return false;
    }

    public Set<Estado> getConjunto(){
        return conjunto;
    }

    Set<Estado> _getConjuntoInterno() {
        return conjunto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AFD)) return false;
        AFD outro = (AFD) o;
        return this.conjunto.equals(outro.conjunto);
    }

    @Override
    public int hashCode() {
        return conjunto.hashCode();
    }

    @Override
    public String toString() {
        if (conjunto.isEmpty()) return "{o}"; // conjunto vazio

        // Ordena os ids dos estados para gerar nome estável
        List<Integer> ids = new ArrayList<>();
        for (Estado e : conjunto) ids.add(e.getNome());
        Collections.sort(ids);

        StringBuilder sb = new StringBuilder();
        for (int id : ids) {
            sb.append(id);
        }
        return sb.toString();
    }
}
