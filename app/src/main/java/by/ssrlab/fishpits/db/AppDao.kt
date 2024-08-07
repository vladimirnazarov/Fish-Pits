package by.ssrlab.fishpits.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import by.ssrlab.fishpits.objects.Region
import by.ssrlab.fishpits.objects.WaterObject
import by.ssrlab.fishpits.objects.district.DistrictDescripted
import by.ssrlab.fishpits.objects.district.DistrictForDB
import by.ssrlab.fishpits.objects.point.PointDescripted
import by.ssrlab.fishpits.objects.point.PointForDB

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistrictDB(items: List<DistrictForDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDistrictDescripted(items: List<DistrictDescripted>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPoints(items: List<PointForDB>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPointsDescripted(items: List<PointDescripted>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRegions(items: List<Region>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWaterObjects(items: List<WaterObject>)



    @Query("SELECT * FROM districtForDb")
    suspend fun getAllDistDb(): List<DistrictForDB>

    @Query("SELECT * FROM districtDescripted")
    suspend fun getAllDistDesc(): List<DistrictDescripted>

    @Query("SELECT * FROM pointForDb")
    suspend fun getAllPoints(): List<PointForDB>

    @Query("SELECT * FROM pointDescripted")
    suspend fun getAllPointsDesc(): List<PointDescripted>

    @Query("SELECT * FROM region")
    suspend fun getAllRegions(): List<Region>

    @Query("SELECT * FROM waterObject")
    suspend fun getAllWO(): List<WaterObject>
}