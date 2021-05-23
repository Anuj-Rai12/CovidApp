package com.example.covidapp.datamodel.statemodel


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "Testing_Table")
data class Tested(
    @SerializedName("dailyrtpcrsamplescollectedicmrapplication")
    @PrimaryKey
    val dailyrtpcrsamplescollectedicmrapplication: String,
    @SerializedName("firstdoseadministered") val firstdoseadministered: String,
    @SerializedName("frontlineworkersvaccinated1stdose") val frontlineworkersvaccinated1stdose: String,
    @SerializedName("frontlineworkersvaccinated2nddose") val frontlineworkersvaccinated2nddose: String,
    @SerializedName("healthcareworkersvaccinated1stdose") val healthcareworkersvaccinated1stdose: String,
    @SerializedName("healthcareworkersvaccinated2nddose") val healthcareworkersvaccinated2nddose: String,
    @SerializedName("over45years1stdose") val over45years1stdose: String,
    @SerializedName("over45years2nddose") val over45years2nddose: String,
    @SerializedName("over60years1stdose") val over60years1stdose: String,
    @SerializedName("over60years2nddose") val over60years2nddose: String,
    @SerializedName("positivecasesfromsamplesreported") val positivecasesfromsamplesreported: String,
    @SerializedName("registration18-45years") val registration1845years: String,
    @SerializedName("registrationabove45years") val registrationabove45years: String,
    @SerializedName("registrationflwhcw") val registrationflwhcw: String,
    @SerializedName("registrationonline") val registrationonline: String,
    @SerializedName("registrationonspot") val registrationonspot: String,
    @SerializedName("samplereportedtoday") val samplereportedtoday: String,
    @SerializedName("seconddoseadministered") val seconddoseadministered: String,
    @SerializedName("source") val source: String,
    @SerializedName("source2") val source2: String,
    @SerializedName("source3") val source3: String,
    @SerializedName("source4") val source4: String,
    @SerializedName("testedasof") val testedasof: String,
    @SerializedName("testsconductedbyprivatelabs") val testsconductedbyprivatelabs: String,
    @SerializedName("to60yearswithco-morbidities1stdose") val to60yearswithcoMorbidities1stdose: String,
    @SerializedName("to60yearswithco-morbidities2nddose") val to60yearswithcoMorbidities2nddose: String,
    @SerializedName("totaldosesadministered") val totaldosesadministered: String,
    @SerializedName("totalindividualsregistered") val totalindividualsregistered: String,
    @SerializedName("totalindividualstested") val totalindividualstested: String,
    @SerializedName("totalindividualsvaccinated") val totalindividualsvaccinated: String,
    @SerializedName("totalpositivecases") val totalpositivecases: String,
    @SerializedName("totalrtpcrsamplescollectedicmrapplication") val totalrtpcrsamplescollectedicmrapplication: String,
    @SerializedName("totalsamplestested") val totalsamplestested: String,
    @SerializedName("totalsessionsconducted") val totalsessionsconducted: String,
    @SerializedName("updatetimestamp") val updatetimestamp: String,
    @SerializedName("years1stdose") val years1stdose: String
)