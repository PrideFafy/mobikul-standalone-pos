package com.webkul.mobikul.mobikulstandalonepos.db;

import static com.webkul.mobikul.mobikulstandalonepos.activity.BaseActivity.TAG;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.ERROR_CODE;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.ERROR_MSG;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.ERROR_MSG_2;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_10_SKU_ALLREADY_EXIST;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_1_ADD_HOLD_CART;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_1_ADD_OPTION;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_1_ADD_TAX_RATE;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_1_SIGN_UP;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_1_UPDATE_ADMIN_DETAILS;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_2_DELETE_OPTION;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_2_DELETE_TAX;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_2_SIGN_IN;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_3_ADD_CATEGORY;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_3_UPDATE_OPTION;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_3_UPDATE_TAX;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_4_UPDATE_CATEGORY;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_5_DELETE_CATEGORY;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_6_ADD_CUSTOMER;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_6_ADD_PRODUCT;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_7_DELETE_CUSTOMER;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_7_DELETE_PRODUCT;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_8_UPDATE_CUSTOMER;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_8_UPDATE_PRODUCT;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_9_CUSTOMER_ALL_READY_EXIST;
import static com.webkul.mobikul.mobikulstandalonepos.constants.ApplicationConstants.SUCCESS_MSG_9_ORDER_PLACED;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.webkul.mobikul.mobikulstandalonepos.db.converters.DataConverter;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.AdministratorDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.CashDrawerDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.CategoryDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.CustomerDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.HoldCartDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.OptionDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.OrderDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.ProductDao;
import com.webkul.mobikul.mobikulstandalonepos.db.dao.TaxDao;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Administrator;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.CashDrawerModel;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Category;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Customer;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.HoldCart;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Options;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.OrderEntity;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Product;
import com.webkul.mobikul.mobikulstandalonepos.db.entity.Tax;
import com.webkul.mobikul.mobikulstandalonepos.db.model.NAdministrator;
import com.webkul.mobikul.mobikulstandalonepos.helper.Helper;
import com.webkul.mobikul.mobikulstandalonepos.interfaces.DataBaseCallBack;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by aman.gupta on 5/1/18.
 */

public class DataBaseAsyncUtils {
    static DataBaseAsyncUtils dataBaseAsyncUtils;

    public static synchronized DataBaseAsyncUtils getInstanse() {
        if (dataBaseAsyncUtils == null)
            dataBaseAsyncUtils = new DataBaseAsyncUtils();
        return dataBaseAsyncUtils;
    }

    class GetAdminByEmailAsyncTask extends AsyncTask<Administrator, Void,
            Administrator> {

        private final DataBaseCallBack dataBaseCallBack;
       // private AppDatabase db;
        private final ConnectionSource dataSource;

        GetAdminByEmailAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           // db = userDatabase;
            this.dataBaseCallBack = dataBaseCallBack;
            this.dataSource = dataSource;
        }

        @Override
        protected Administrator doInBackground(Administrator... administrators) {
            Administrator administrator;
            try {
                AdministratorDao administratorDao
                        = DaoManager.createDao(dataSource, Administrator.class);
                administrator = administratorDao.findByEmail(administrators[0].getEmail(), administrators[0].getPassword());
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return administrator;
        }

        @Override
        protected void onPostExecute(Administrator administrator) {
            super.onPostExecute(administrator);
            if (administrator != null)
                dataBaseCallBack.onSuccess(administrator, SUCCESS_MSG_2_SIGN_IN);
            else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG_2);
        }
    }

    class GetAllAdminAsyncTask extends AsyncTask<Void, Void,
            Administrator> {

        private final DataBaseCallBack dataBaseCallBack;
       // private AppDatabase db;
       private final ConnectionSource dataSource;

        GetAllAdminAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Administrator doInBackground(Void... voids) {
            Administrator administrator;
            try {
                AdministratorDao administratorDao
                        = DaoManager.createDao(dataSource, Administrator.class);
                administrator = administratorDao.getAll();
                NAdministrator nAdministrator ;

                Dao<NAdministrator,Integer> nAdministratorsDao =
                        DaoManager.createDao(Helper.defaultCon, NAdministrator.class);
                nAdministratorsDao.queryForAll();


            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return administrator;
        }

        @Override
        protected void onPostExecute(Administrator administrator) {
            super.onPostExecute(administrator);
            if (administrator != null)
                dataBaseCallBack.onSuccess(administrator, SUCCESS_MSG_2_SIGN_IN);
            else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG_2);
        }
    }

    public class AddAdminAsyncTask extends AsyncTask<Administrator, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public AddAdminAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Administrator... administrators) {
            try {
                AdministratorDao administratorDao
                        = DaoManager.createDao(dataSource, Administrator.class);
                administratorDao.insertAll(administrators);
                Dao<NAdministrator,Integer> nAdministratorsDao =
                        DaoManager.createDao(Helper.defaultCon, NAdministrator.class);
                NAdministrator nAdministrator = new NAdministrator();
                nAdministrator.setEmail("admin");
                nAdministrator.setFirstName("admin");
                nAdministrator.setLastName("admin");
                nAdministrator.setPassword("admin");
               // nAdministratorsDao.create(nAdministrator);
                nAdministratorsDao.createIfNotExists(nAdministrator);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(true, SUCCESS_MSG_1_SIGN_UP);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class UpdateAdmin extends AsyncTask<Administrator, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public UpdateAdmin(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Administrator... administrator) {
            try {
                AdministratorDao administratorDao
                        = DaoManager.createDao(dataSource, Administrator.class);
                administratorDao.updateAdminById(administrator[0].getFirstName(), administrator[0].getLastName(), administrator[0].getUsername(), administrator[0].getUid());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(true, SUCCESS_MSG_1_UPDATE_ADMIN_DETAILS);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class AddCategoryAsyncTask extends AsyncTask<Category, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public AddCategoryAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource =dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Category... categories) {
            try {
                CategoryDao categoryDao = DaoManager.createDao(dataSource, Category.class);
                categoryDao.insertAll(categories);
            } catch (Exception e) {
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(true, SUCCESS_MSG_3_ADD_CATEGORY);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetCategoryAsyncTask extends AsyncTask<Void, Void,
            List<Category>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetCategoryAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           this.dataSource =dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            try {
                CategoryDao categoryDao = DaoManager.createDao(dataSource, Category.class);
                List<Category> categories = categoryDao.getAll();
                return categories;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<Category> categoryList) {
            super.onPostExecute(categoryList);
            if (categoryList != null) {
                dataBaseCallBack.onSuccess(categoryList, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }

    }


    public class GetDrawerIncludedCategories extends AsyncTask<Void, Void,
            List<Category>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetDrawerIncludedCategories(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            try {
                CategoryDao categoryDao = DaoManager.createDao(dataSource, Category.class);
                List<Category> categories = categoryDao.getCategoryIncludedInDrawerMenu(true, true);
                return categories;
            } catch (SQLException throwables) {

                throwables.printStackTrace();
                return null;
            }

        }

        @Override
        protected void onPostExecute(List<Category> categoryList) {
            super.onPostExecute(categoryList);
            if (categoryList != null) {
                dataBaseCallBack.onSuccess(categoryList, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }

    }

    public class UpdateCategoryById extends AsyncTask<Category, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public UpdateCategoryById(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Category... categories) {
            try {
                CategoryDao categoryDao = DaoManager.createDao(dataSource, Category.class);
                categoryDao.updateCategoryById(categories[0].getCategoryName(), categories[0].isActive(), categories[0].isIncludeInDrawerMenu(), categories[0].getCId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_4_UPDATE_CATEGORY);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class DeleteCategoryById extends AsyncTask<Category, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public DeleteCategoryById(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Category... categories) {
            try {
                CategoryDao categoryDao = DaoManager.createDao(dataSource, Category.class);
               categoryDao.delete(categories[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_5_DELETE_CATEGORY);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class AddProductAsyncTask extends AsyncTask<Product, Void,
            Long> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        public AddProductAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Long doInBackground(Product... products) {
            long[] id;
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                id = productDao.insertAll(products);
            } catch (Exception e) {
                e.printStackTrace();
                return Long.valueOf(0);
            }
            return id[0];
        }

        @Override
        protected void onPostExecute(Long id) {
            super.onPostExecute(id);
            if (id != 0) {
                dataBaseCallBack.onSuccess(id, SUCCESS_MSG_6_ADD_PRODUCT);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class UpdateProductImages extends AsyncTask<Void, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final String imagePath;
        private final Long pId;
        private final DataBaseCallBack callBack;

        public UpdateProductImages(ConnectionSource dataSource, String imagePath, Long pId, DataBaseCallBack callBack) {
           this.dataSource = dataSource;
            this.imagePath = imagePath;
            this.pId = pId;
            this.callBack = callBack;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            long[] id;
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
               productDao.updateProductImages(imagePath, pId);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success)
                callBack.onSuccess(success, SUCCESS_MSG_6_ADD_PRODUCT);
            else
                callBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetAllProducts extends AsyncTask<Void, Void,
            List<Product>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetAllProducts(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                List<Product> products = productDao.getAll();
                return products;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            if (products != null) {
                dataBaseCallBack.onSuccess(products, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetAllEnabledProducts extends AsyncTask<Void, Void,
            List<Product>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetAllEnabledProducts(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Product> doInBackground(Void... voids) {
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                List<Product> products = productDao.getEnabledProduct(true);
                return products;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            if (products != null) {
                dataBaseCallBack.onSuccess(products, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetAllLowStockProducts extends AsyncTask<Integer, Void,
            List<Product>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetAllLowStockProducts(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Product> doInBackground(Integer... qty) {
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                List<Product> products = productDao.getLowStockProducts(qty[0]);
                return products;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<Product> products) {
            super.onPostExecute(products);
            if (products != null) {
                dataBaseCallBack.onSuccess(products, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class UpdateProduct extends AsyncTask<Product, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public UpdateProduct(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Product... products) {
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                productDao.updateProduct(products[0].getImage()
                        , products[0].isEnabled()
                        , products[0].getProductName()
                        , products[0].getSku()
                        , products[0].getPrice()
                        , products[0].getSpecialPrice()
                        , products[0].isTaxableGoodsApplied()
                        , products[0].isTrackInventory()
                        , products[0].getQuantity()
                        , products[0].isStock()
                        , products[0].getWeight()
                        , (new DataConverter()).fromProductCategoriesList(products[0].getProductCategories())
                        , (new DataConverter()).fromOptionList(products[0].getOptions())
                        , (new DataConverter()).fromTaxModelToString(products[0].getProductTax())
                        , products[0].getPId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_8_UPDATE_PRODUCT);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class UpdateProductQty extends AsyncTask<Product, Void,
            Boolean> {

        private final Context context;
        private final ConnectionSource dataSource;

        public UpdateProductQty(Context context, ConnectionSource dataSource) {
            this.context = context;
            this.dataSource = dataSource;
        }

        @Override
        protected Boolean doInBackground(Product... products) {
            try {
//                Log.d(TAG, "doInBackground: qty" + Integer.parseInt(products[0].getQuantity()) + "---" + Integer.parseInt(products[0].getCartQty()));
//                if (!AppSharedPref.isReturnCart(context))
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
               productDao.updateProductQty(Integer.parseInt(products[0].getQuantity()) - Integer.parseInt(products[0].getCartQty()) + ""
                        , products[0].getPId());
//                else
//                    db.productDao().updateProductQty(Integer.parseInt(products[0].getQuantity()) + Integer.parseInt(products[0].getCartQty()) + ""
//                            , products[0].getPId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    public class DeleteProduct extends AsyncTask<Product, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public DeleteProduct(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Product... products) {
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                productDao.delete(products[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_7_DELETE_PRODUCT);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class CheckSkuExist extends AsyncTask<String, Void,
            Product> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public CheckSkuExist(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Product doInBackground(String... sku) {
            Product product = null;
            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                product = productDao.checkSkuExist(sku[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return product;
        }

        @Override
        protected void onPostExecute(Product product) {
            super.onPostExecute(product);
            if (product != null) {
                dataBaseCallBack.onSuccess(product, SUCCESS_MSG_10_SKU_ALLREADY_EXIST);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class GetAllCustomers extends AsyncTask<Void, Void,
            List<Customer>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetAllCustomers(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Customer> doInBackground(Void... voids) {
            try {
                CustomerDao customerDao = DaoManager.createDao(dataSource, Customer.class);
                List<Customer> customers = customerDao.getAll();
                return customers;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<Customer> customers) {
            super.onPostExecute(customers);
            if (customers != null) {
                dataBaseCallBack.onSuccess(customers, SUCCESS_MSG);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }


    public class AddCustomerAsyncTask extends AsyncTask<Customer, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public AddCustomerAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Customer... customers) {
            try {
                CustomerDao customerDao = DaoManager.createDao(dataSource, Customer.class);
                customerDao.insertAll(customers[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(true, SUCCESS_MSG_6_ADD_CUSTOMER);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }


    public class UpdateCustomerAsyncTask extends AsyncTask<Customer, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public UpdateCustomerAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Customer... customers) {
            try {
                CustomerDao customerDao = DaoManager.createDao(dataSource, Customer.class);
                customerDao.updateCustomerById(customers[0].getFirstName()
                        , customers[0].getLastName()
                        , customers[0].getEmail()
                        , customers[0].getContactNumber()
                        , customers[0].getAddressLine()
                        , customers[0].getCity()
                        , customers[0].getPostalCode()
                        , customers[0].getState()
                        , customers[0].getCountry()
                        , customers[0].getCustomerId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(true, SUCCESS_MSG_8_UPDATE_CUSTOMER);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class DeleteCustomer extends AsyncTask<Customer, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public DeleteCustomer(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           this.dataSource =dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Customer... customers) {
            try {
                CustomerDao customerDao = DaoManager.createDao(dataSource, Customer.class);
                customerDao.delete(customers[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_7_DELETE_CUSTOMER);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class CheckEmailExist extends AsyncTask<String, Void,
            Customer> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public CheckEmailExist(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
           this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Customer doInBackground(String... email) {
            Customer customer;
            try {
                CustomerDao customerDao = DaoManager.createDao(dataSource, Customer.class);
                customer = customerDao.checkEmailExist(email[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
            return customer;
        }

        @Override
        protected void onPostExecute(Customer customer) {
            super.onPostExecute(customer);
            if (customer != null) {
                dataBaseCallBack.onSuccess(customer, SUCCESS_MSG_9_CUSTOMER_ALL_READY_EXIST);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class CheckNumberExist extends AsyncTask<String, Void,
            Customer> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public CheckNumberExist(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Customer doInBackground(String... number) {
            Customer customer = null;
            try {
                CustomerDao customerDao = DaoManager.createDao(dataSource, Customer.class);
                customer = customerDao.checkNumberExist(number[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return customer;
        }

        @Override
        protected void onPostExecute(Customer customer) {
            super.onPostExecute(customer);
            if (customer != null) {
                dataBaseCallBack.onSuccess(customer, SUCCESS_MSG_9_CUSTOMER_ALL_READY_EXIST);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class GenerateOrderAsyncTask extends AsyncTask<OrderEntity, Void,
            Long> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GenerateOrderAsyncTask(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Long doInBackground(OrderEntity... orders) {
            long[] id;
            try {
                OrderDao orderDao = DaoManager.createDao(dataSource, OrderEntity.class);
                id = orderDao.insertAll(orders[0]);
                Log.d(TAG, "doInBackground: " + id[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return Long.valueOf(0);
            }
            return id[0];
        }

        @Override
        protected void onPostExecute(Long orderId) {
            super.onPostExecute(orderId);
            if (orderId != 0) {
                dataBaseCallBack.onSuccess(orderId, SUCCESS_MSG_9_ORDER_PLACED);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class UpdateRefundedOrderId extends AsyncTask<Void, Void,
            Boolean> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;
        private String returnedOrderId;
        private String currentOrderId;

        public UpdateRefundedOrderId(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack, String returnedOrderId, String currentOrderId) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
            this.returnedOrderId = returnedOrderId;
            this.currentOrderId = currentOrderId;
            this.returnedOrderId = returnedOrderId;
            this.currentOrderId = currentOrderId;
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            long[] id;
            try {
                OrderDao orderDao = DaoManager.createDao(dataSource, OrderEntity.class);
                orderDao.updateRefundedOrderId(currentOrderId, Integer.parseInt(returnedOrderId) - 10000);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                dataBaseCallBack.onSuccess(success, SUCCESS_MSG_9_ORDER_PLACED);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetOrders extends AsyncTask<Void, Void,
            List<OrderEntity>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetOrders(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<OrderEntity> doInBackground(Void... voids) {
            try {
                OrderDao orderDao = DaoManager.createDao(dataSource, OrderEntity.class);
                List<OrderEntity> orders = orderDao.getAll();
                return orders;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
           return  null;
        }

        @Override
        protected void onPostExecute(List<OrderEntity> orders) {
            super.onPostExecute(orders);
            if (orders != null) {
                dataBaseCallBack.onSuccess(orders, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetOrdersById extends AsyncTask<String, Void,
            OrderEntity> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetOrdersById(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected OrderEntity doInBackground(String... orderIds) {
            try {
                OrderDao orderDao = DaoManager.createDao(dataSource, OrderEntity.class);
                OrderEntity orders = orderDao.loadByIds(Integer.parseInt(orderIds[0]));
                return orders;
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
           return  null;
        }

        @Override
        protected void onPostExecute(OrderEntity order) {
            super.onPostExecute(order);
            if (order != null) {
                dataBaseCallBack.onSuccess(order, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetSearchData extends AsyncTask<String, Void,
            List<Product>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetSearchData(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Product> doInBackground(String... texts) {

            try {
                ProductDao productDao = DaoManager.createDao(dataSource, Product.class);
                return productDao.getSearchData(texts[0]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<Product> searchData) {
            super.onPostExecute(searchData);
            if (searchData != null) {
                dataBaseCallBack.onSuccess(searchData, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetSearchOrders extends AsyncTask<String, Void,
            List<OrderEntity>> {

        private final ConnectionSource dataSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetSearchOrders(ConnectionSource dataSource, DataBaseCallBack dataBaseCallBack) {
            this.dataSource = dataSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<OrderEntity> doInBackground(String... texts) {

            try {
                OrderDao orderDao = DaoManager.createDao(dataSource, OrderEntity.class);
                return orderDao.getSearchOrders(texts[0]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return  null;
        }

        @Override
        protected void onPostExecute(List<OrderEntity> searchData) {
            super.onPostExecute(searchData);
            if (searchData != null) {
                dataBaseCallBack.onSuccess(searchData, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class DeleteAllTables extends AsyncTask<Void, Void,
            Void> {

        private final ConnectionSource connectionSource;

        public DeleteAllTables(ConnectionSource connectionSource) {
            this.connectionSource = connectionSource;
        }

        @Override
        protected Void doInBackground(Void... texts) {
            try {
                OrderDao orderDao = DaoManager.createDao(connectionSource, OrderEntity.class);
                orderDao.delete();
            }catch (Exception e){

            }
//            db.orderDao().delete();
//            db.productDao().delete();
//            db.categoryDao().delete();
//            db.customerDao().delete();
//            db.holdCartDao().delete();
//            db.optionDao().delete();
//            db.cashDrawerDao().delete();
//            db.taxDao().delete();
            return null;
        }
    }

    public class AddCartDataToHoldCart extends AsyncTask<HoldCart, Void,
            Long> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public AddCartDataToHoldCart(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Long doInBackground(HoldCart... holdCarts) {
            long[] id;
            try {
                HoldCartDao holdCartDao = DaoManager.createDao(connectionSource, HoldCart.class);
                id = holdCartDao.insertAll(holdCarts[0]);
                Log.d(TAG, "doInBackground: " + id[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return Long.valueOf(0);
            }
            return id[0];
        }

        @Override
        protected void onPostExecute(Long orderId) {
            super.onPostExecute(orderId);
            if (orderId != 0) {
                dataBaseCallBack.onSuccess(orderId, SUCCESS_MSG_1_ADD_HOLD_CART);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetHoldCartData extends AsyncTask<Void, Void,
            List<HoldCart>> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetHoldCartData(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<HoldCart> doInBackground(Void... voids) {

            List<HoldCart> holdCarts = null;
            try {
                HoldCartDao holdCartDao = DaoManager.createDao(connectionSource, HoldCart.class);
                holdCarts = holdCartDao.getAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return holdCarts;
        }

        @Override
        protected void onPostExecute(List<HoldCart> holdCarts) {
            super.onPostExecute(holdCarts);
            if (holdCarts != null) {
                dataBaseCallBack.onSuccess(holdCarts, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class DeleteHoldCartById extends AsyncTask<HoldCart, Void,
            Boolean> {

        private final ConnectionSource connectionSource;

        public DeleteHoldCartById(ConnectionSource connectionSource) {
            this.connectionSource = connectionSource;
        }

        @Override
        protected Boolean doInBackground(HoldCart... holdCarts) {
            try {
                HoldCartDao holdCartDao = DaoManager.createDao(connectionSource, HoldCart.class);
                holdCartDao.delete(holdCarts[0].getHoldCartId() - 12000);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
        }
    }

    public class GetProductByBarcode extends AsyncTask<String, Void,
            Product> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetProductByBarcode(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Product doInBackground(String... texts) {
            try {
                ProductDao productDao = DaoManager.createDao(connectionSource, Product.class);
                return productDao.getProductByBarcode(texts[0]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
           return  null;
        }

        @Override
        protected void onPostExecute(Product searchData) {
            super.onPostExecute(searchData);
            if (searchData != null) {
                dataBaseCallBack.onSuccess(searchData, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class AddCashDrawerData extends AsyncTask<CashDrawerModel, Void,
            Void> {

        private final ConnectionSource connectionSource;

        public AddCashDrawerData(ConnectionSource connectionSource) {
            this.connectionSource = connectionSource;
        }

        @Override
        protected Void doInBackground(CashDrawerModel... cashDrawerModels) {
            try {
                CashDrawerDao cashDrawerDao = DaoManager.createDao(connectionSource, CashDrawerModel.class);
               cashDrawerDao.insertAll(cashDrawerModels[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public class UpdateCashData extends AsyncTask<CashDrawerModel, Void,
            Boolean> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack callBack;

        public UpdateCashData(ConnectionSource connectionSource, DataBaseCallBack callBack) {
            this.connectionSource = connectionSource;
            this.callBack = callBack;
        }

        @Override
        protected Boolean doInBackground(CashDrawerModel... cashDrawerModel) {
            try {
                CashDrawerDao cashDrawerDao = DaoManager.createDao(connectionSource, CashDrawerModel.class);
                DataConverter converter = new DataConverter();
                cashDrawerDao.updateCashDrawerModelByDate(cashDrawerModel[0].getClosingBalance()
                        , cashDrawerModel[0].getNetRevenue()
                        , cashDrawerModel[0].getInAmount()
                        , cashDrawerModel[0].getOutAmount()
                        , converter.fromCashDrawerItemToString(cashDrawerModel[0].getCashDrawerItems())
                        , cashDrawerModel[0].getFormattedClosingBalance()
                        , cashDrawerModel[0].getFormattedNetRevenue()
                        , cashDrawerModel[0].getFormattedInAmount()
                        , cashDrawerModel[0].getFormattedOutAmount()
                        , cashDrawerModel[0].getDate());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean success) {
            super.onPostExecute(success);
            if (success) {
                callBack.onSuccess(success, "Update!");
            } else {
                callBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class GetCashDrawerDataByDate extends AsyncTask<String, Void,
            CashDrawerModel> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetCashDrawerDataByDate(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected CashDrawerModel doInBackground(String... date) {
            try {
                CashDrawerDao cashDrawerDao = DaoManager.createDao(connectionSource, CashDrawerModel.class);
                return cashDrawerDao.loadAllByDate(date[0]);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
           return null;
        }

        @Override
        protected void onPostExecute(CashDrawerModel cashDrawerModel) {
            super.onPostExecute(cashDrawerModel);
            if (cashDrawerModel != null) {
                dataBaseCallBack.onSuccess(cashDrawerModel, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetAllCashDrawerData extends AsyncTask<Void, Void,
            List<CashDrawerModel>> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetAllCashDrawerData(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<CashDrawerModel> doInBackground(Void... voids) {

            try {
                CashDrawerDao cashDrawerDao = DaoManager.createDao(connectionSource, CashDrawerModel.class);
                return cashDrawerDao.getAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<CashDrawerModel> cashDrawerModelList) {
            super.onPostExecute(cashDrawerModelList);
            if (cashDrawerModelList != null) {
                dataBaseCallBack.onSuccess(cashDrawerModelList, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class AddOptions extends AsyncTask<Options, Void,
            Long> {

        private final ConnectionSource connectionSource ;
        private final DataBaseCallBack dataBaseCallBack;

        public AddOptions(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
           this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Long doInBackground(Options... options) {
            try {
                OptionDao optionDao = DaoManager.createDao(connectionSource, Options.class);
                Long[] id = optionDao.insertAll(options);
                return id[0];
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Long id) {
            super.onPostExecute(id);
            if (id != null) {
                dataBaseCallBack.onSuccess(id, SUCCESS_MSG_1_ADD_OPTION);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetOptions extends AsyncTask<Void, Void,
            List<Options>> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetOptions(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Options> doInBackground(Void... voids) {

            try {
                OptionDao optionDao = DaoManager.createDao(connectionSource, Options.class);
                return optionDao.getAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Options> options) {
            super.onPostExecute(options);
            if (options != null) {
                dataBaseCallBack.onSuccess(options, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class UpdateOptions extends AsyncTask<Options, Void,
            Boolean> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public UpdateOptions(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Options... options) {
            try {
                OptionDao optionDao = DaoManager.createDao(connectionSource, Options.class);
               optionDao.updateOptionsById(options[0].getOptionName()
                        , options[0].getType()
                        , (new DataConverter()).fromOptionValuesList(options[0].getOptionValues())
                        , options[0].getOptionId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_3_UPDATE_OPTION);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }

    public class DeleteOption extends AsyncTask<Options, Void,
            Boolean> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public DeleteOption(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
           this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Options... options) {
            try {
                OptionDao optionDao = DaoManager.createDao(connectionSource, Options.class);
               optionDao.delete(options[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_2_DELETE_OPTION);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }


    public class AddTaxRate extends AsyncTask<Tax, Void,
            Long> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public AddTaxRate(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Long doInBackground(Tax... taxes) {

            Long[] id = new Long[0];
            try {
                TaxDao taxDao = DaoManager.createDao(connectionSource, Tax.class);
                id = taxDao.insertAll(taxes);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return id[0];
        }

        @Override
        protected void onPostExecute(Long id) {
            super.onPostExecute(id);
            if (id != null) {
                dataBaseCallBack.onSuccess(id, SUCCESS_MSG_1_ADD_TAX_RATE);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class GetAllTaxes extends AsyncTask<Void, Void,
            List<Tax>> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetAllTaxes(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Tax> doInBackground(Void... voids) {

            List<Tax> taxes = null;
            try {
                TaxDao taxDao = DaoManager.createDao(connectionSource, Tax.class);
                taxes = taxDao.getAll();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return taxes;
        }

        @Override
        protected void onPostExecute(List<Tax> taxes) {
            super.onPostExecute(taxes);
            if (taxes != null) {
                dataBaseCallBack.onSuccess(taxes, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }

    public class UpdateTaxRate extends AsyncTask<Tax, Void,
            Boolean> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public UpdateTaxRate(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Tax... taxes) {
            try {
                TaxDao taxDao = DaoManager.createDao(connectionSource, Tax.class);
                taxDao.updateTaxById(taxes[0].getTaxName(), taxes[0].isEnabled(), taxes[0].getTaxRate(), taxes[0].getTaxId());
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(true, SUCCESS_MSG_3_UPDATE_TAX);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }


    public class GetAllEnabledTaxes extends AsyncTask<Void, Void,
            List<Tax>> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public GetAllEnabledTaxes(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected List<Tax> doInBackground(Void... voids) {

            List<Tax> tax = null;
            try {
                TaxDao taxDao = DaoManager.createDao(connectionSource, Tax.class);
                tax = taxDao.getEnabledTax(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            return tax;
        }

        @Override
        protected void onPostExecute(List<Tax> taxList) {
            super.onPostExecute(taxList);
            if (taxList != null) {
                dataBaseCallBack.onSuccess(taxList, SUCCESS_MSG);
            } else
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
        }
    }


    public class DeleteTax extends AsyncTask<Tax, Void,
            Boolean> {

        private final ConnectionSource connectionSource;
        private final DataBaseCallBack dataBaseCallBack;

        public DeleteTax(ConnectionSource connectionSource, DataBaseCallBack dataBaseCallBack) {
            this.connectionSource = connectionSource;
            this.dataBaseCallBack = dataBaseCallBack;
        }

        @Override
        protected Boolean doInBackground(Tax... taxes) {
            try {
                TaxDao taxDao = DaoManager.createDao(connectionSource, Tax.class);
                taxDao.delete(taxes[0]);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if (aBoolean) {
                dataBaseCallBack.onSuccess(aBoolean, SUCCESS_MSG_2_DELETE_TAX);
            } else {
                dataBaseCallBack.onFailure(ERROR_CODE, ERROR_MSG);
            }
        }
    }
}