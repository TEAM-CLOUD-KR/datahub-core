DATAHUB_CORE_REPOSITORY=/home/datahub
PROJECT_NAME=datahub-core


echo "> Check pid of Running Application"

CURRENT_PID=$(pgrep -f ${PROJECT_NAME})
echo "> pid: $CURRENT_PID"

echo "> kill -15 $CURRENT_PID"
sudo kill -15 $CURRENT_PID
sleep 5

echo "> Start Project Build"
cd $DATAHUB_CORE_REPOSITORY/$PROJECT_NAME/
sudo chmod 755 gradlew
sudo ./gradlew build -x test

echo "> step1. Change Directory"
cd $DATAHUB_CORE_REPOSITORY/$PROJECT_NAME

echo "> Copy Build File"
sudo cp $DATAHUB_CORE_REPOSITORY/$PROJECT_NAME/build/libs/*.jar $DATAHUB_CORE_REPOSITORY/


echo "> Deploy New Application"

JAR_NAME=$(ls -tr $DATAHUB_CORE_REPOSITORY/ | grep "\.jar$" | tail -n 1)

echo "> JAR Name: $JAR_NAME"


sudo nohup java -jar \
    $DATAHUB_CORE_REPOSITORY/$JAR_NAME > $DATAHUB_CORE_REPOSITORY/datahub_core.out 2>&1 &