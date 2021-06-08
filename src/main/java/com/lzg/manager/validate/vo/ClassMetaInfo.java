package com.lzg.manager.validate.vo;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

/**
 * @author luoZhaoGong
 * @date 2021/6/2
 * @Description:
 */
@Data
public class ClassMetaInfo {
    private String className;
    private List<MethodMetaInfo> methods = new ArrayList<>();

    public static ClassMetaInfo of(String className) {
        ClassMetaInfo classMetaInfo = new ClassMetaInfo();
        classMetaInfo.className = className;
        return classMetaInfo;
    }

    public ClassMetaInfo addMethod(MethodMetaInfo methodMetaInfo) {
        methods.add(methodMetaInfo);
        return this;
    }

    @Data
    public static class MethodMetaInfo {
        private String methodName;
        private List<ParamMetaInfo> params = new ArrayList<>();

        public static MethodMetaInfo of(String methodName) {
            MethodMetaInfo methodMetaInfo = new MethodMetaInfo();
            methodMetaInfo.methodName = methodName;
            return methodMetaInfo;
        }

        public MethodMetaInfo addParam(ParamMetaInfo paramMetaInfo) {
            params.add(paramMetaInfo);
            return this;
        }

        @Override
        public String toString() {
            return "MethodMetaInfo{" +
                    "methodName='" + methodName + '\'' +
                    ", params=" + params +
                    '}';
        }
    }

    @Data
    public static class ParamMetaInfo {
        private String className;
        private String paramName;

        public static ParamMetaInfo of(String className, String paramName) {
            ParamMetaInfo paramMetaInfo = new ParamMetaInfo();
            paramMetaInfo.className = className;
            paramMetaInfo.paramName = paramName;
            return paramMetaInfo;
        }

        @Override
        public String toString() {
            return "ParamMetaInfo{" +
                    "className='" + className + '\'' +
                    ", paramName='" + paramName + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "ClassMetaInfo{" +
                "className='" + className + '\'' +
                ", methods=" + methods +
                '}';
    }
}
