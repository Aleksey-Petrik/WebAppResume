package com.webapp;

/*
 *
 * Ленивый singleton загрузка происходит по требованию, а не сразу.
 *
 * Внутренний механизм джавы не даст нескольким потокам создать новые экземпляры, а всего лишь один
 * */
public class LazySingletonConcurrency {
    volatile private static LazySingletonConcurrency INSTANCE;

    private LazySingletonConcurrency() {
    }

    private static class LazySingletonHolder {
        private static final LazySingletonConcurrency INSTANCE = new LazySingletonConcurrency();
    }

    public static LazySingletonConcurrency getInstance() {
        return LazySingletonHolder.INSTANCE;
    }
}
