package utils.viewer

interface ConsistentViewer<GK, IK, V> : GetViewer<GK, V>, InvokeViewer<IK, V>
