package de.himberger.jackson.builder;

public class Messages {

	public static final String ERR_ANNOTATION_CLASS_NULL = "The provided annotation class must not be null";
	
	public static final String ERR_CLASS_NULL = "The provided class must not be null or empty";

	public static final String ERR_BUILDER_HAS_ARGS = "The method %s of class %s is annotated als a builder-factory method but expects arguments. Builder-factory methods do not take arguments";

	public static final String ERR_METHOD_NAME_NULL = "The provided method name must not be null or empty";

	public static final String ERR_CREATE_INSTANCE_FINDER_NULL = "The create instance MethodFinder must not be null";

	public static final String ERR_CREATE_BUILDER_FINDER_NULL = "The create builder MethodFinder must not be null";;

	public static final String ERR_OBJECT_MAPPER_NULL = "The provided ObjectMapper must not be null";
	
}
