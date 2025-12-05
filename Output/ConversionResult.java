package Output;

import java.util.Map;
import java.util.Set;
import Estrutura.AFD;

public class ConversionResult {
    public Set<AFD> estadosAFD;
    public Map<AFD, Map<String, AFD>> tabela;
    public AFD inicial;

    public ConversionResult(Set<AFD> estadosAFD, Map<AFD, Map<String, AFD>> tabela, AFD inicial) {
        this.estadosAFD = estadosAFD;
        this.tabela = tabela;
        this.inicial = inicial;
    }
}
