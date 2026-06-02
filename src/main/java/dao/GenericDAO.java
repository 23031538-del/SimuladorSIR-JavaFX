package dao;

import java.util.List;

public interface GenericDAO<T> {

    void insertar(T objeto);

    T obtenerPorId(int id);

    List<T> listar();

    void actualizar(T objeto);

    void eliminar(int id);

}
