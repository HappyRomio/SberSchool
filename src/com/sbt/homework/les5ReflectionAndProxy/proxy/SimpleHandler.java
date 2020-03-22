package com.sbt.homework.les5ReflectionAndProxy.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SimpleHandler implements InvocationHandler {
    private TestProxy testProxy;
    private Map<ArgsHash, Object> cache;
    public SimpleHandler(TestProxy testProxy){
        this.testProxy = testProxy;
        cache = new HashMap<>();
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        ArgsHash argsHash = new ArgsHash(method,args);
        if(cache.containsKey(argsHash)){
            System.out.println("Hello from cahe " + cache.get(argsHash));
            return cache.get(argsHash);
        }else{
            cache.put(argsHash,method.invoke(testProxy,args));
            return cache.get(argsHash);
        }
    }

    private static final class ArgsHash {
        private final Method method;
        private final Object[] args;
        private final Integer hash;

        public ArgsHash(Method method, Object[] args) {
            this.method = method;
            this.args = args;
            hash = calculateHash();
        }

        private Integer calculateHash() {
                int hashMethod = method.hashCode();
                if(args!=null) {
                    for (Object o : args) {
                        hashMethod = hashMethod * 75896 + (o == null ? 0 : o.hashCode());
                    }
                }
                return hashMethod;
        }

        @Override
        public int hashCode(){
            return hash;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            ArgsHash argsHash = (ArgsHash) o;
            return method.equals(argsHash.method) &&
                    Arrays.equals(args, argsHash.args);
        }

    }
}
