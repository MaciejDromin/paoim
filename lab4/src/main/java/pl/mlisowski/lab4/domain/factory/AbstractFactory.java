package pl.mlisowski.lab4.domain.factory;

public interface AbstractFactory <E, S> {

    S from(E from);

    E to(S to);

}
