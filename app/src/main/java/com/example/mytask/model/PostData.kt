package com.example.mytask.model

import com.google.gson.annotations.SerializedName

data class PostData (

    @SerializedName("posts" ) var posts : ArrayList<Posts> = arrayListOf(),
    @SerializedName("total" ) var total : Int?             = null,
    @SerializedName("skip"  ) var skip  : Int?             = null,
    @SerializedName("limit" ) var limit : Int?             = null

)

data class Posts (

    @SerializedName("id"        ) var id        : Int?              = null,
    @SerializedName("title"     ) var title     : String?           = null,
    @SerializedName("body"      ) var body      : String?           = null,
    @SerializedName("tags"      ) var tags      : ArrayList<String> = arrayListOf(),
    @SerializedName("reactions" ) var reactions : Reactions?        = Reactions(),
    @SerializedName("views"     ) var views     : Int?              = null,
    @SerializedName("userId"    ) var userId    : Int?              = null

)


data class Reactions (

    @SerializedName("likes"    ) var likes    : Int? = null,
    @SerializedName("dislikes" ) var dislikes : Int? = null

)