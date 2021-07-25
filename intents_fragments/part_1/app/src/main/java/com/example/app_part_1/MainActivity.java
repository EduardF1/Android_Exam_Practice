package com.example.app_part_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

/*
    AndroidManifest.xml contents:

    <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.app_part_1">

    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.App_part_1">
        <activity android:name=".Activity5" />
        <activity android:name=".Activity4" />
        <activity android:name=".Activity3" />
        <activity android:name=".Activity2" />
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>

    Q.2
    Create a blank app with an empty Activity. Add four new activities to the app.
    Navigate to the AndroidManifest.xml file under the app>manifest folders in the project directory.
    How does the file change as you add more activities to the app?

    As more activities are added to the app and the manifest file is the app configuration file,
    the newly added activities are added to the manifest with specific name references.
    Ex.:  <activity android:name=".Activity4" />

    Consider:
    In the manifest, what does the value of the name attribute of each Activity tag correspond to?
    -    <activity android:name=".Activity4" /> is automatically prefixed by the system with the
    package name specified in the manifest  <manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.example.app_part_1"> as the "." (dot) character is used.

    What is the purpose of the intent-filter element?
    -   It specifies the capabilities of an activity, service or broadcast receiver. It basically
    describes the capabilities of its parent component - what an activity or service can do and
    what types of broadcasts a receiver can handle. It opens the component to receiving intents of
    the advertised type while filtering out those that are not meaningful for the component.
    Intent-filter subelements are: <action>, <category> and <data>.

    What element MUST the intent-filter contain? What happens if there is no such element present?
    -   An intent-filter must contain an <action> subelement. If there is no such element, the
    filter doesn't accept any Intent objects.

    What elements CAN the intent-filter contain?
    -   An intent-filter can contain the <category> and <data> attributes.

    What do these elements do? (hint: have a look through the API guide)
    -   The <category> attribute is a string containing additional information about the kind of
    component that should handle the intent. Any number of category descriptions can be placed in
    an intent, but most intents do not require a category.
    Common categories:
    CATEGORY_BROWSABLE : the target activity allows itself to be started by a web browser to display
    data referenced by a link, such as an image or an e-mail message.
    CATEGORY_LAUNCHER: the activity is the initial activity of a task and is listed in the system's
    application launcher.

    - The <data> attribute : The URI (a Uri object) that references the data to be acted on and/or
    the MIME type of that data. The type of data supplied is generally dictated by the intent's
    action. For example, if the action is ACTION_EDIT, the data should contain the URI of the
    document to edit.When creating an intent, it's often important to specify the type of data
    (its MIME type) in addition to its URI. For example, an activity that's able to display images
    probably won't be able to play an audio file, even though the URI formats could be similar.
    Specifying the MIME type of your data helps the Android system find the best component to
    receive your intent. However, the MIME type can sometimes be inferred from the URI —
    particularly when the data is a content: URI. A content: URI indicates the data is located on
    the device and controlled by a ContentProvider, which makes the data MIME type visible to the
    system.
 */