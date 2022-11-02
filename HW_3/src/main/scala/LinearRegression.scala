import LinearRegression.DATA_PATH
import breeze.linalg._
import breeze.numerics._
import java.io.File

object LinearRegression {
  val DATA_PATH = "./data/Boston.csv"
  val PREDICTIONS_PATH = "./data/predictions.csv"
  val train_size = 0.7

  def main(args: Array[String]): Unit = {
    println("Loading the data ...")

    var data = read_data(DATA_PATH)
    var (data_train, data_test, target_train, target_test) = split_train_val_data(data)

    println("Training Linear Regression ...")
    var weights_lr = train(data_train, target_train)
    var predictions_train = predict(weights_lr, data_train)
    var mse_train = calc_mse(predictions_train, target_train)
    println("Training is finished!")
    println(s"MSE on train: ${mse_train}")
    println("Predict on test ...")

    var predictions_test = predict(weights_lr, data_test)
    var mse_test = calc_mse(predictions_test, target_test)
    println(s"MSE on test: ${mse_test}")


    save_data(predictions_test, PREDICTIONS_PATH)
    println("Predictions are saved successfully")

  }

  def read_data(DATA_PATH: String): DenseMatrix[Double] = {
    val path = new File(DATA_PATH)
    val data = csvread(path, separator = ',', skipLines = 1)
    data
  }

  def save_data(data: DenseVector[Double], DATA_PATH: String): Unit = {
    val path = new File(DATA_PATH)
    csvwrite(path, data.asDenseMatrix, ',')
  }

  def split_train_val_data(data: DenseMatrix[Double]): (
    DenseMatrix[Double],
    DenseMatrix[Double],
    DenseVector[Double],
    DenseVector[Double]) = {

    var train_size = (0.8 * data.rows).toInt

    val X = data(::, 0 to -2)
    val y = data(::, -1)

    val X_train = X(0 to train_size, ::)
    val X_test = X(train_size to -1, ::)
    val y_train = y(0 to train_size)
    val y_test = y(train_size to -1)

    (X_train, X_test, y_train, y_test)
  }

  def train(X_train: DenseMatrix[Double], y_train: DenseVector[Double]): DenseVector[Double] = {
    var vec_ones = DenseMatrix.ones[Double](X_train.rows, 1)

    var X = DenseMatrix.horzcat(vec_ones, X_train)
    var weights = inv(X.t * X) * X.t * y_train
    weights
  }

  def predict(weights: DenseVector[Double], X_test: DenseMatrix[Double]): DenseVector[Double] = {
    var vec_ones = DenseMatrix.ones[Double](X_test.rows, 1)
    var X = DenseMatrix.horzcat(vec_ones, X_test)
    var predictions = X * weights
    predictions
  }

  def calc_mse(predictions: DenseVector[Double], y: DenseVector[Double]): Double = {
    var mse_metric = sum(pow((y - predictions), 2)) / y.length
    mse_metric
  }
}

