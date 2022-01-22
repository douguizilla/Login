package br.proto.services;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.43.2)",
    comments = "Source: services.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class GrpcHashServiceGrpc {

  private GrpcHashServiceGrpc() {}

  public static final String SERVICE_NAME = "br.proto.services.GrpcHashService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<br.proto.services.Services.CreateRequest,
      br.proto.services.Services.CreateResponse> getCreateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "create",
      requestType = br.proto.services.Services.CreateRequest.class,
      responseType = br.proto.services.Services.CreateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.proto.services.Services.CreateRequest,
      br.proto.services.Services.CreateResponse> getCreateMethod() {
    io.grpc.MethodDescriptor<br.proto.services.Services.CreateRequest, br.proto.services.Services.CreateResponse> getCreateMethod;
    if ((getCreateMethod = GrpcHashServiceGrpc.getCreateMethod) == null) {
      synchronized (GrpcHashServiceGrpc.class) {
        if ((getCreateMethod = GrpcHashServiceGrpc.getCreateMethod) == null) {
          GrpcHashServiceGrpc.getCreateMethod = getCreateMethod =
              io.grpc.MethodDescriptor.<br.proto.services.Services.CreateRequest, br.proto.services.Services.CreateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "create"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.CreateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.CreateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcHashServiceMethodDescriptorSupplier("create"))
              .build();
        }
      }
    }
    return getCreateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.proto.services.Services.ReadRequest,
      br.proto.services.Services.ReadResponse> getReadMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "read",
      requestType = br.proto.services.Services.ReadRequest.class,
      responseType = br.proto.services.Services.ReadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.proto.services.Services.ReadRequest,
      br.proto.services.Services.ReadResponse> getReadMethod() {
    io.grpc.MethodDescriptor<br.proto.services.Services.ReadRequest, br.proto.services.Services.ReadResponse> getReadMethod;
    if ((getReadMethod = GrpcHashServiceGrpc.getReadMethod) == null) {
      synchronized (GrpcHashServiceGrpc.class) {
        if ((getReadMethod = GrpcHashServiceGrpc.getReadMethod) == null) {
          GrpcHashServiceGrpc.getReadMethod = getReadMethod =
              io.grpc.MethodDescriptor.<br.proto.services.Services.ReadRequest, br.proto.services.Services.ReadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "read"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.ReadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.ReadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcHashServiceMethodDescriptorSupplier("read"))
              .build();
        }
      }
    }
    return getReadMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.proto.services.Services.UpdateRequest,
      br.proto.services.Services.UpdateResponse> getUpdateMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "update",
      requestType = br.proto.services.Services.UpdateRequest.class,
      responseType = br.proto.services.Services.UpdateResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.proto.services.Services.UpdateRequest,
      br.proto.services.Services.UpdateResponse> getUpdateMethod() {
    io.grpc.MethodDescriptor<br.proto.services.Services.UpdateRequest, br.proto.services.Services.UpdateResponse> getUpdateMethod;
    if ((getUpdateMethod = GrpcHashServiceGrpc.getUpdateMethod) == null) {
      synchronized (GrpcHashServiceGrpc.class) {
        if ((getUpdateMethod = GrpcHashServiceGrpc.getUpdateMethod) == null) {
          GrpcHashServiceGrpc.getUpdateMethod = getUpdateMethod =
              io.grpc.MethodDescriptor.<br.proto.services.Services.UpdateRequest, br.proto.services.Services.UpdateResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "update"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.UpdateRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.UpdateResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcHashServiceMethodDescriptorSupplier("update"))
              .build();
        }
      }
    }
    return getUpdateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.proto.services.Services.DeleteRequest,
      br.proto.services.Services.DeleteResponse> getDeleteMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "delete",
      requestType = br.proto.services.Services.DeleteRequest.class,
      responseType = br.proto.services.Services.DeleteResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.proto.services.Services.DeleteRequest,
      br.proto.services.Services.DeleteResponse> getDeleteMethod() {
    io.grpc.MethodDescriptor<br.proto.services.Services.DeleteRequest, br.proto.services.Services.DeleteResponse> getDeleteMethod;
    if ((getDeleteMethod = GrpcHashServiceGrpc.getDeleteMethod) == null) {
      synchronized (GrpcHashServiceGrpc.class) {
        if ((getDeleteMethod = GrpcHashServiceGrpc.getDeleteMethod) == null) {
          GrpcHashServiceGrpc.getDeleteMethod = getDeleteMethod =
              io.grpc.MethodDescriptor.<br.proto.services.Services.DeleteRequest, br.proto.services.Services.DeleteResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "delete"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.DeleteRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.DeleteResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcHashServiceMethodDescriptorSupplier("delete"))
              .build();
        }
      }
    }
    return getDeleteMethod;
  }

  private static volatile io.grpc.MethodDescriptor<br.proto.services.Services.ExitRequest,
      br.proto.services.Services.ExitResponse> getExitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "exit",
      requestType = br.proto.services.Services.ExitRequest.class,
      responseType = br.proto.services.Services.ExitResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<br.proto.services.Services.ExitRequest,
      br.proto.services.Services.ExitResponse> getExitMethod() {
    io.grpc.MethodDescriptor<br.proto.services.Services.ExitRequest, br.proto.services.Services.ExitResponse> getExitMethod;
    if ((getExitMethod = GrpcHashServiceGrpc.getExitMethod) == null) {
      synchronized (GrpcHashServiceGrpc.class) {
        if ((getExitMethod = GrpcHashServiceGrpc.getExitMethod) == null) {
          GrpcHashServiceGrpc.getExitMethod = getExitMethod =
              io.grpc.MethodDescriptor.<br.proto.services.Services.ExitRequest, br.proto.services.Services.ExitResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "exit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.ExitRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  br.proto.services.Services.ExitResponse.getDefaultInstance()))
              .setSchemaDescriptor(new GrpcHashServiceMethodDescriptorSupplier("exit"))
              .build();
        }
      }
    }
    return getExitMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static GrpcHashServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcHashServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcHashServiceStub>() {
        @java.lang.Override
        public GrpcHashServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcHashServiceStub(channel, callOptions);
        }
      };
    return GrpcHashServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static GrpcHashServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcHashServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcHashServiceBlockingStub>() {
        @java.lang.Override
        public GrpcHashServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcHashServiceBlockingStub(channel, callOptions);
        }
      };
    return GrpcHashServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static GrpcHashServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<GrpcHashServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<GrpcHashServiceFutureStub>() {
        @java.lang.Override
        public GrpcHashServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new GrpcHashServiceFutureStub(channel, callOptions);
        }
      };
    return GrpcHashServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class GrpcHashServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void create(br.proto.services.Services.CreateRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.CreateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateMethod(), responseObserver);
    }

    /**
     */
    public void read(br.proto.services.Services.ReadRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.ReadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getReadMethod(), responseObserver);
    }

    /**
     */
    public void update(br.proto.services.Services.UpdateRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.UpdateResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateMethod(), responseObserver);
    }

    /**
     */
    public void delete(br.proto.services.Services.DeleteRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.DeleteResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteMethod(), responseObserver);
    }

    /**
     */
    public void exit(br.proto.services.Services.ExitRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.ExitResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getExitMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCreateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                br.proto.services.Services.CreateRequest,
                br.proto.services.Services.CreateResponse>(
                  this, METHODID_CREATE)))
          .addMethod(
            getReadMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                br.proto.services.Services.ReadRequest,
                br.proto.services.Services.ReadResponse>(
                  this, METHODID_READ)))
          .addMethod(
            getUpdateMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                br.proto.services.Services.UpdateRequest,
                br.proto.services.Services.UpdateResponse>(
                  this, METHODID_UPDATE)))
          .addMethod(
            getDeleteMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                br.proto.services.Services.DeleteRequest,
                br.proto.services.Services.DeleteResponse>(
                  this, METHODID_DELETE)))
          .addMethod(
            getExitMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                br.proto.services.Services.ExitRequest,
                br.proto.services.Services.ExitResponse>(
                  this, METHODID_EXIT)))
          .build();
    }
  }

  /**
   */
  public static final class GrpcHashServiceStub extends io.grpc.stub.AbstractAsyncStub<GrpcHashServiceStub> {
    private GrpcHashServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcHashServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcHashServiceStub(channel, callOptions);
    }

    /**
     */
    public void create(br.proto.services.Services.CreateRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.CreateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void read(br.proto.services.Services.ReadRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.ReadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void update(br.proto.services.Services.UpdateRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.UpdateResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void delete(br.proto.services.Services.DeleteRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.DeleteResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void exit(br.proto.services.Services.ExitRequest request,
        io.grpc.stub.StreamObserver<br.proto.services.Services.ExitResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getExitMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class GrpcHashServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<GrpcHashServiceBlockingStub> {
    private GrpcHashServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcHashServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcHashServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public br.proto.services.Services.CreateResponse create(br.proto.services.Services.CreateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.proto.services.Services.ReadResponse read(br.proto.services.Services.ReadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getReadMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.proto.services.Services.UpdateResponse update(br.proto.services.Services.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.proto.services.Services.DeleteResponse delete(br.proto.services.Services.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteMethod(), getCallOptions(), request);
    }

    /**
     */
    public br.proto.services.Services.ExitResponse exit(br.proto.services.Services.ExitRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getExitMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class GrpcHashServiceFutureStub extends io.grpc.stub.AbstractFutureStub<GrpcHashServiceFutureStub> {
    private GrpcHashServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected GrpcHashServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new GrpcHashServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.proto.services.Services.CreateResponse> create(
        br.proto.services.Services.CreateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.proto.services.Services.ReadResponse> read(
        br.proto.services.Services.ReadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getReadMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.proto.services.Services.UpdateResponse> update(
        br.proto.services.Services.UpdateRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.proto.services.Services.DeleteResponse> delete(
        br.proto.services.Services.DeleteRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<br.proto.services.Services.ExitResponse> exit(
        br.proto.services.Services.ExitRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getExitMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE = 0;
  private static final int METHODID_READ = 1;
  private static final int METHODID_UPDATE = 2;
  private static final int METHODID_DELETE = 3;
  private static final int METHODID_EXIT = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final GrpcHashServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(GrpcHashServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE:
          serviceImpl.create((br.proto.services.Services.CreateRequest) request,
              (io.grpc.stub.StreamObserver<br.proto.services.Services.CreateResponse>) responseObserver);
          break;
        case METHODID_READ:
          serviceImpl.read((br.proto.services.Services.ReadRequest) request,
              (io.grpc.stub.StreamObserver<br.proto.services.Services.ReadResponse>) responseObserver);
          break;
        case METHODID_UPDATE:
          serviceImpl.update((br.proto.services.Services.UpdateRequest) request,
              (io.grpc.stub.StreamObserver<br.proto.services.Services.UpdateResponse>) responseObserver);
          break;
        case METHODID_DELETE:
          serviceImpl.delete((br.proto.services.Services.DeleteRequest) request,
              (io.grpc.stub.StreamObserver<br.proto.services.Services.DeleteResponse>) responseObserver);
          break;
        case METHODID_EXIT:
          serviceImpl.exit((br.proto.services.Services.ExitRequest) request,
              (io.grpc.stub.StreamObserver<br.proto.services.Services.ExitResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class GrpcHashServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    GrpcHashServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return br.proto.services.Services.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("GrpcHashService");
    }
  }

  private static final class GrpcHashServiceFileDescriptorSupplier
      extends GrpcHashServiceBaseDescriptorSupplier {
    GrpcHashServiceFileDescriptorSupplier() {}
  }

  private static final class GrpcHashServiceMethodDescriptorSupplier
      extends GrpcHashServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    GrpcHashServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (GrpcHashServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new GrpcHashServiceFileDescriptorSupplier())
              .addMethod(getCreateMethod())
              .addMethod(getReadMethod())
              .addMethod(getUpdateMethod())
              .addMethod(getDeleteMethod())
              .addMethod(getExitMethod())
              .build();
        }
      }
    }
    return result;
  }
}
