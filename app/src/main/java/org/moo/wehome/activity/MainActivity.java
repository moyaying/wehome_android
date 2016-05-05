package org.moo.wehome.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.moo.framework.domain.action.ActionScheduler;
import org.moo.framework.domain.action.DefaultSubscriber;
import org.moo.wehome.R;
import org.moo.wehome.common.UIHelper;
import org.moo.wehome.domain.entity.User;
import org.moo.wehome.domain.manager.IAccountManager;

import javax.inject.Inject;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Inject
    IAccountManager accountManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivityComponent().inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_travel) {//发去出行
            UIHelper.toastMessage(this, "发起出行");

//            repository.getMainPageCache().subscribe(entity -> {
//                Log.w("MainActivity", "getMainPageCache cache:" + (entity == null ? "null" : entity.getSelectstr()));
//            });

//            ActionRunner.run(repository.getMainPageData(), new DefaultSubscriber<MainPageEntity>(){
//                @Override
//                public void onNext(MainPageEntity mainPageEntity) {
//                    super.onNext(mainPageEntity);
//                    Log.w("MainActivity", "onNext ZhiBoMainPageEntity:" + (mainPageEntity == null ? "null" : mainPageEntity.getSelectstr()));
//                }
//            });
//            repository.getMainPageData()
//                    .compose(ActionScheduler.executeSchedulers())
//                    .subscribe(entity -> {
//                        Log.w("MainActivity", "getMainPageCache ZhiBoMainPageEntity:" + (entity == null ? "null" : entity.getSelectstr()));
//                    });

//            repository.getMainPageCache()
            accountManager.login("moo", "test")
                    .compose(ActionScheduler.executeSchedulers())
                    .subscribe(new DefaultSubscriber<User>() {
                        @Override
                        public void onNext(User user) {
                            super.onNext(user);
                            Toast.makeText(MainActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(Throwable e) {
                            super.onError(e);
                            Toast.makeText(MainActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                        }
                    });

            return true;
        } else if (id == R.id.action_order) {
            UIHelper.toastMessage(this, "发起要去");
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
