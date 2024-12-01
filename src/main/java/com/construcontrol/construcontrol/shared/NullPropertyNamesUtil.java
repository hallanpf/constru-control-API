package com.construcontrol.construcontrol.shared;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import java.util.HashSet;
import java.util.Set;

public class NullPropertyNamesUtil {
    // Método estático para obter os nomes das propriedades nulas de um objeto
    public static String[] getNullPropertyNames(Object source) {
        // Cria um BeanWrapper para o objeto de origem
        final BeanWrapper src = new BeanWrapperImpl(source);
        // Obtém os descritores de propriedade do objeto
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();
        // Conjunto para armazenar os nomes das propriedades nulas
        Set<String> emptyNames = new HashSet<>();
        // Itera sobre os descritores de propriedade
        for (java.beans.PropertyDescriptor pd : pds) {
            // Obtém o valor da propriedade atual
            Object srcValue = src.getPropertyValue(pd.getName());
            // Verifica se o valor é nulo
            if (srcValue == null) {
                // Se for nulo, adiciona o nome da propriedade ao conjunto de DADOS
                emptyNames.add(pd.getName());
            }
        }
        // Converte o conjunto em um array de strings e retorna
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
