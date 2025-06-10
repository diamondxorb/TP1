package util;

import java.util.HashMap;
import java.util.Map;

public class MotivosVistoria {
    private static final Map<String, String> MOTIVOS_DESCRICOES = new HashMap<>();

    static {
        MOTIVOS_DESCRICOES.put("Troca de motor", "Vistoria para regularização de troca do motor do veículo");
        MOTIVOS_DESCRICOES.put("Gravação de chassi", "Vistoria para verificação e gravação de chassi");
        MOTIVOS_DESCRICOES.put("Suspensão", "Vistoria para verificação de modificações no sistema de suspensão");
        MOTIVOS_DESCRICOES.put("Coleção", "Vistoria especial para veículos de coleção");
        MOTIVOS_DESCRICOES.put("Leilão", "Vistoria para veículos adquiridos em leilão");
        MOTIVOS_DESCRICOES.put("Troca de Carroceria", "Vistoria para regularização de troca de carroceria");
        MOTIVOS_DESCRICOES.put("Media Monta (Retirar restrição)", "Vistoria para retirada de restrição de média monta");
        MOTIVOS_DESCRICOES.put("1ª emplacamento (nota vencida)", "Vistoria para primeiro emplacamento com nota fiscal vencida");
        MOTIVOS_DESCRICOES.put("Roubo e furto (retirar restrição)", "Vistoria para retirar restrição de roubo/furto");
        MOTIVOS_DESCRICOES.put("Inclusão de CSV", "Vistoria para inclusão de Certificado de Segurança Veicular");
        MOTIVOS_DESCRICOES.put("Retirar GNV", "Vistoria para retirada de sistema GNV do veículo");
        MOTIVOS_DESCRICOES.put("Vistoria lacrada", "Vistoria especial para veículos lacrados");
    }

    public static String[] getMotivos() {
        return MOTIVOS_DESCRICOES.keySet().toArray(new String[0]);
    }

    public static String getDescricao(String motivo) {
        return MOTIVOS_DESCRICOES.getOrDefault(motivo, "Descrição não disponível");
    }
}