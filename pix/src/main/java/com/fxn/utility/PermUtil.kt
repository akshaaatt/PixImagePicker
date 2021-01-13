package com.fxn.utility

import android.Manifest
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import com.fxn.interfaces.WorkFinish
import java.util.*

/**
 * Created by akshay on 11/14/16.
 */
object PermUtil {
    const val REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS = 9921

    /*
    @RequiresApi(api = Build.VERSION_CODES.M)
    public static void checkForPermissions(final FragmentActivity activity) {
        List<String> permissionsNeeded = new ArrayList<String>();
        final List<String> permissionsList = new ArrayList<String>();
        if (!addPermission(permissionsList, Manifest.permission.CAMERA, activity))
            permissionsNeeded.add("CAMERA");
        if (!addPermission(permissionsList, android.Manifest.permission.WRITE_EXTERNAL_STORAGE, activity))
            permissionsNeeded.add("WRITE_EXTERNAL_STORAGE");
        if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_FINE_LOCATION, activity))
            permissionsNeeded.add("ACCESS_FINE_LOCATION");
        if (!addPermission(permissionsList, android.Manifest.permission.ACCESS_COARSE_LOCATION, activity))
            permissionsNeeded.add("ACCESS_COARSE_LOCATION");
        if (permissionsList.size() > 0) {
            activity.requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                    REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS);
        }
    }*/
    @RequiresApi(api = Build.VERSION_CODES.M)
    private fun addPermission(permissionsList: MutableList<String>, permission: String, ac: Activity?): Boolean {
        if (ac!!.checkSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
            permissionsList.add(permission)
            // Check for Rationale Option
            return ac.shouldShowRequestPermissionRationale(permission)
        }
        return true
    }

    fun checkForCamaraWritePermissions(activity: FragmentActivity, workFinish: WorkFinish) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            workFinish.onWorkFinish(true)
        } else {
            val permissionsNeeded: MutableList<String> = ArrayList()
            val permissionsList: MutableList<String> = ArrayList()
            if (!addPermission(permissionsList, Manifest.permission.CAMERA, activity)) permissionsNeeded.add("CAMERA")
            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE, activity)) permissionsNeeded.add("WRITE_EXTERNAL_STORAGE")
            if (permissionsList.size > 0) {
                activity.requestPermissions(permissionsList.toTypedArray(),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)
            } else {
                workFinish.onWorkFinish(true)
            }
        }
    }

    @JvmStatic
    fun checkForCamaraWritePermissions(fragment: Fragment, workFinish: WorkFinish) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            workFinish.onWorkFinish(true)
        } else {
            val permissionsNeeded: MutableList<String> = ArrayList()
            val permissionsList: MutableList<String> = ArrayList()
            if (!addPermission(permissionsList, Manifest.permission.CAMERA, fragment.activity)) permissionsNeeded.add("CAMERA")
            if (!addPermission(permissionsList, Manifest.permission.WRITE_EXTERNAL_STORAGE, fragment.activity)) permissionsNeeded.add("WRITE_EXTERNAL_STORAGE")
            if (permissionsList.size > 0) {
                fragment.requestPermissions(permissionsList.toTypedArray(),
                        REQUEST_CODE_ASK_MULTIPLE_PERMISSIONS)
            } else {
                workFinish.onWorkFinish(true)
            }
        }
    }
}