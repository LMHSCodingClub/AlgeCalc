interface OperationalEntity {
    OperationalEntity plus(OperationalEntity addend);

    OperationalEntity minus(OperationalEntity subtrahend);

    OperationalEntity times(OperationalEntity factor);

    OperationalEntity divideBy(OperationalEntity dividend);

    OperationalEntity pow(OperationalEntity exponent);

    OperationalEntity sqrt();

    OperationalEntity cbrt();
}
