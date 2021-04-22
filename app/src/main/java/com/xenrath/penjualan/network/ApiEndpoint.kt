package com.xenrath.penjualan.network

import com.xenrath.penjualan.data.model.agent.ResponseAgentDetail
import com.xenrath.penjualan.data.model.agent.ResponseAgentList
import com.xenrath.penjualan.data.model.agent.ResponseAgentUpdate
import com.xenrath.penjualan.data.model.cart.ResponseCartList
import com.xenrath.penjualan.data.model.cart.ResponseCartUpdate
import com.xenrath.penjualan.data.model.cart.ResponseCheckout
import com.xenrath.penjualan.data.model.login.ResponseLogin
import com.xenrath.penjualan.data.model.product.ResponseCategoryList
import com.xenrath.penjualan.data.model.product.ResponseProductList
import com.xenrath.penjualan.data.model.transaction.ResponseTransactionList
import com.xenrath.penjualan.data.model.transaction.detail.ResponseTransactionDetail
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface ApiEndpoint {

    @FormUrlEncoded
    @POST("login_employee")
    fun loginEmployee(
        @Field("username") username: String,
        @Field("password") password: String,
    ): Call<ResponseLogin>

    @GET("agent")
    fun getAgent(): Call<ResponseAgentList>

    @Multipart
    @POST("agent")
    fun insertAgent(
        @Query("store") store: String,
        @Query("owner") owner: String,
        @Query("address") address: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part image: MultipartBody.Part
    ) : Call<ResponseAgentUpdate>

    @GET("agent/{id}")
    fun getAgentDetail(
        @Path("id") id: Long
    ): Call<ResponseAgentDetail>

    @Multipart
    @POST("agent/{id}")
    fun updateAgent(
        @Path("id") id: Long,
        @Query("store") store: String,
        @Query("owner") owner: String,
        @Query("address") address: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Part image: MultipartBody.Part,
        @Query("_method") _method: String,
    ) : Call<ResponseAgentUpdate>

    @DELETE("agent/{id}")
    fun deleteAgent(
        @Path("id") id: Long
    ): Call<ResponseAgentUpdate>

    @FormUrlEncoded
    @POST("get_transaction")
    fun getTransaction(
        @Field("username") username: String
    ): Call<ResponseTransactionList>

    @FormUrlEncoded
    @POST("get_detail_transaction")
    fun getDetailTransaction(
        @Field("invoice_number") invoice_number: String
    ): Call<ResponseTransactionDetail>

    @FormUrlEncoded
    @POST("get_cart")
    fun getCart(
        @Field("username") username: String
    ): Call<ResponseCartList>

    @FormUrlEncoded
    @POST("add_cart")
    fun addCart(
        @Field("username") username: String,
        @Field("product_id") product_id: Long,
        @Field("count") count: Long
    ): Call<ResponseCartUpdate>

    @GET("get_category")
    fun getCategory(): Call<ResponseCategoryList>

    @FormUrlEncoded
    @POST("get_category_product")
    fun getProduct(
        @Field("category_id") category_id: Long
    ): Call<ResponseProductList>

    @FormUrlEncoded
    @POST("delete_item_cart")
    fun deleteItemCart(
        @Field("id") id: Long
    ): Call<ResponseCartUpdate>

    @FormUrlEncoded
    @POST("delete_cart")
    fun deleteCart(
        @Field("username") username: String
    ): Call<ResponseCartUpdate>

    @GET("search_agent")
    fun searchAgent(
        @Query("keyword") keyword: String
    ): Call<ResponseAgentList>

    @FormUrlEncoded
    @POST("checkout")
    fun checkOut(
        @Field("username") username: String,
        @Field("agent_id") agent_id: Long,
    ): Call<ResponseCheckout>

}